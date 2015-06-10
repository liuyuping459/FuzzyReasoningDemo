# FuzzyReasoningDemo
A fuzzy reasoning demo achieved by using Jboss drools rule engine.
---
The rete loading time after the first is short and stable. No matter how many your rules are, the loading time would only increase a little.

You can use coding to create rule file (.drl).
In it, the rule 0 is a rule for control circulation.
Other rules like this:
   
    rule "4"    //rule number
  	  salience 4 
  	  no-loop true
  	
      when
  		$message16 : Message(a == 16)   //antecedent element
  		$assistant : Assistant()
      
      then
      	//System.out.println(4);
  
  		Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();
   		factAndWeight.put(16, 1.0);   //antecedent element and it's weight in this rule
   		$assistant.inferenceOneStep(17, factAndWeight, 90);   //consequent, ..., reliability of the rule
  
   		insert(new Message(0));
    end

---
I'm sorry that I haven't some real rules. Or I have, but can not represent them in public. So they are some noun and capital letters.
if you use it, you cloud replace them for your rules.
