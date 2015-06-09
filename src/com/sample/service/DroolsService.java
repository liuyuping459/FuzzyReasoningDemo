package com.sample.service;

import java.util.List;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.sample.function.Assistant;
import com.sample.function.Values;
import com.sample.bean.Message;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsService {
	private boolean flag = true;
	private boolean startFlag = true;
	private StatefulKnowledgeSession ksession = null;
	private Assistant assistant = new Assistant();

	public synchronized void ksessionReady() {
		if (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			if (startFlag) {
				System.out.println("# Please wait, rete loading...");
				startFlag = false;
			}
			long startTime = System.currentTimeMillis();
			KnowledgeBase kbase = readKnowledgeBase();
			ksession = kbase.newStatefulKnowledgeSession();
			System.out.println("# Rete ready (using " + (System.currentTimeMillis() - startTime) + "ms)");
			
			System.out.println("# Please input your fact numbers, everyone end by press Enter.");
			System.out.println("# If you have none, please press 0 and Enter for finish.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.notify();
		flag = false;
	}

    public synchronized void startDrools(Set<Message> set) {
        if (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    	try {
            for (Message message : set) {
            	ksession.insert(message);			//插入对象
            	assistant.getReliabilityMap().put(message.isA(), message.getR());
			}
            ksession.insert(assistant);			//插入对象
            ksession.fireAllRules();			//开始执行规则引擎
        } catch (Throwable t) {
            t.printStackTrace();
        }
    	showTree();
		assistant = new Assistant();
		
		this.notify();
		flag = true;
    }

	public void showTree() {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#00.00%");
		for (Integer integer : assistant.getConclusionSet()) {
			if(false == assistant.getTree().hasParent(integer)) {
				assistant.getTree().add(0, integer);
			}
		}
		List<Integer> list = assistant.getTree().formatList(0, 0);
		for (int i = 0; i < list.size(); i+=2) {
			for (int j = 0; j < list.get(i+1) - 1; j++) {
				System.out.print("	");
			}
			if (1 != list.get(i + 1)) {
				System.out.print("└———");
			}
			System.out.print(Values.num2Words(list.get(i)));
			System.out.print("(" + df.format(assistant.getReliabilityMap().get(list.get(i))) + ")");
			System.out.println();
		}
	}

    private static KnowledgeBase readKnowledgeBase() throws Exception {
    	
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("Sample.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }
}
