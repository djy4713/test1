package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/*
 * ²éÕÒ×åÆ×
 */
public class Main {
	
	static class Node{
		public Node parent;
		public List<Node> childList;
		public String name;
		
		public Node(){
			childList = new ArrayList<Node>();
		}
		
	}
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Node root=null;
		Map<String,Node> nodeMap = new HashMap<String , Node>();
		int counts = Integer.parseInt(in.nextLine());
		for(int i=0 ; i<counts ; i++){
			String line = in.nextLine();
			//System.out.println("--------"+line+"-----------");
			String[] temp = line.split("\\s{1,}");
			if(temp[0].equals("AddRelationShip")){
				if(root==null){
					root = new Node();
					root.name = temp[1];
					root.parent=null;
					
					Node node = new Node();
					node.name = temp[2];
					node.parent = root;
					
					root.childList.add(node);
					nodeMap.put(root.name, root);
					nodeMap.put(node.name, node);
				}else{
					Node parentNode = nodeMap.get(temp[1]);
					if(parentNode==null){
						parentNode = new Node();
						parentNode.name = temp[1];
						parentNode.parent=null;
					}
					
					Node childNode = new Node();
					childNode.name = temp[2];
					childNode.parent = parentNode;
					
					parentNode.childList.add(childNode) ;
					nodeMap.put(parentNode.name, parentNode);
					nodeMap.put(childNode.name, childNode);
				}
			}else if(temp[0].equals("GetGeneration")){
				Node person1 = nodeMap.get(temp[1]);
				Node person2 = nodeMap.get(temp[2]);
					
				if(person1==null || person2==null || person1.name.equals(person2.name))
					System.out.println(-1);
				else{
					System.out.println(getGetGeneration(person1,person2));
					
				}
			}else if(temp[0].equals("GetCousin")){
				Node person1 = nodeMap.get(temp[1]);
				Node person2 = nodeMap.get(temp[2]);
				Node ancestor = getGetCommonAncestor(person1,person2);
				if(ancestor==null)
					System.out.println(-1);
				else{
					int Generation,Cousin;
					if(c1<c2){
						Cousin=c2-c1;
						Generation = c1;
					}
					else{
						Cousin=c1-c2;
						Generation = c2;
					}
					System.out.println(Generation+" "+Cousin);
				}
			}
		}

	}
	public static int c1=-1,c2=-1;
	public static int getGetGeneration(Node person1,Node person2){
		int result=0;
		while(person1.parent!=null){
			person1 = person1.parent;
			result++;
			if(person1.name.equals(person2.name))
				return result;
			
		}
		result=0;
		while(person2.parent!=null){
			person2 = person2.parent;
			result++;
			if(person2.name.equals(person1.name))
				return result;
			
		}
		return -1;
	}
	public static Node getGetCommonAncestor(Node person1,Node person2){
		c1=-1;c2=-1;
		if(person1==null || person2==null || person1.name.equals(person2.name))
			return null;
		else{
			while(person1.parent!=null){
				person1 = person1.parent;
				Node tNode = person2;
				c1++;
				while(tNode!=null){
					if(person1.name.equals(tNode.name)){
						return person1;
					}else{
						tNode=tNode.parent; 
						c2++;
					}
				}
				c2=-1;
			}
		}
		return null;
	}

}
