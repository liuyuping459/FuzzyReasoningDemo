package com.sample.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sample.bean.Message;
import com.sample.function.TreeService;

public class Assistant {
	//�Ƴ���Ԫ���ݼ�
	private Set<Integer> conclusionSet = new HashSet<Integer>();
	
	public Set<Integer> getConclusionSet() {
		return conclusionSet;
	}
	//������
	private TreeService tree = new TreeService();
	
	public TreeService getTree() {
		return tree;
	}
	//Ԫ���ݶ�Ӧ�Ŀ��Ŷ�
	private Map<Integer, Double> reliabilityMap = new HashMap<Integer, Double>();
	
	public Map<Integer, Double> getReliabilityMap() {
		return reliabilityMap;
	}
	//��¼һ�������Ƴ�����ʵ
	private List<Message> messageList = new ArrayList<Message>();
	
	public List<Message> getMessageList() {
		return messageList;
	}
	//����һ������
	public void inferenceOneStep(Integer conclusion, Map<Integer, Double> factAndWeight, Integer rulesReliability) {
		double r = 0.0;//Ԫ���ݿ��Ŷ�
		messageList.add(new Message(conclusion));//��¼��ǰ����Ϊ�����Ƴ�����ʵ
		Set<Integer> key = factAndWeight.keySet();//ȡ����������ʵȨ��map��key��set����
		for (Integer condition : key) {
			if (0.0 != factAndWeight.get(condition)) {
				r+=(reliabilityMap.get(condition)*factAndWeight.get(condition));//��Ȩƽ�������������Ŀ��Ŷ�
			}
		}
		r = r*rulesReliability/100;// ������۵Ŀ��Ŷ�
		if (!reliabilityMap.containsKey(conclusion)) {  // �����Ԫ�����ڱ���������û�г��ֹ���������Ŷȼ�����Ŷ�map��
			reliabilityMap.put(conclusion, r);
		}
		else { // ��Ȼ����Ԫ�����ڱ��������г��ֹ�������(f1+f2-f1f2)��������Ŷȣ��޸Ŀ��Ŷ�map
			reliabilityMap.put(conclusion, (reliabilityMap.get(conclusion) * (1 - r) + r));
		}
		tree.adds(conclusion, key);//����������tree
		conclusionSet.add(conclusion);// �����Ƴ��Ľ���Ԫ����set��
	}
}

