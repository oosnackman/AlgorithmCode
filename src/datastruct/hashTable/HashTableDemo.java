package datastruct.hashTable;

import java.util.Scanner;

public class HashTableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashTable hashTable = new HashTable(7);

		// 簡單的測試

		String key = "";
		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.println("add: 添加雇員");
			System.out.println("list: 顯示雇員");
			System.out.println("find: 查找雇員");
			System.out.println("exit: 退出系統");

			key = scanner.next();

			switch (key) {
			case "add":
				System.out.println("輸入id");
				int id = scanner.nextInt();
				System.out.println("輸入名字");
				String name = scanner.next();

				Emp emp = new Emp(id, name);
				hashTable.add(emp);

				break;
			case "list":
				hashTable.list();
			case "find":
				System.out.println("請輸入要查找的id");
				 id = scanner.nextInt();
				hashTable.FindEmpById(id);
				break;
			case "exit":
				scanner.close();
				System.exit(0);
				break;

			default:
				break;
			}

		}
		
		
		
	}

}

//表示一個雇員
class Emp {

	public int id;
	public String name;
	public Emp next;

	public Emp(int id, String name) {
		this.id = id;
		this.name = name;
	}

}

//創建HashTable 管理多條鍊錶
class HashTable {

	private EmpLinkedList[] empLinkedListArray;

	private int size;

	public HashTable(int size) {
		this.size = size;
		empLinkedListArray = new EmpLinkedList[size];
		
		//要記得分別出始化..Class
		for (int i = 0; i <size; i++) {
			empLinkedListArray[i]= new EmpLinkedList();
		}
	}

	// 添加雇員
	public void add(Emp emp) {
		// 根據員工的id,得到該員工應該添加到哪條鍊錶
		int empLinkedListNo = hashFun(emp.id);

		empLinkedListArray[empLinkedListNo].add(emp);

	}

	// 顯示所有練表,hashtable
	public void list() {

		for (int i = 0; i < size; i++) {
			empLinkedListArray[i].list(i);
		}

	}
	
    //查詢
	public void FindEmpById(int id) {
		// 根據員工的id,得到該員工應該添加到哪條鍊錶
		int empLinkedListNo = hashFun(id);
		Emp emp =empLinkedListArray[empLinkedListNo].FindEmpById(id);
		
		if (emp!=null) {
			System.out.printf("在第%d條找到該 雇員id=%d\n",(empLinkedListNo+1),emp.id);
		}else {
			System.out.println("在哈希錶中,沒有找到該雇員");
		}
	}

	// 散列含數,使用一個簡單方法,讓emp加到對應的列表
	public int hashFun(int id) {
		return id % size;
	}
}

//表示鍊錶
class EmpLinkedList {
	// 頭指針,執行第一個Emp
	private Emp head = null;

	// 添加雇員到練表
	// 假定,添加時,id是自增長
	public void add(Emp emp) {

		// 第一個雇員
		if (head == null) {
			head = emp;
			return;
		}

		Emp cursor = head;
		while (true) {
			if (cursor.next == null) {
				break;
			}
			cursor = cursor.next;
		}

		cursor.next = emp;
	}

	// 列表出資料
	public void list(int no) {

		if (head == null) {
			System.out.println("第"+(no+1)+"當前鍊表無空");
			return;
		}

		System.out.print("第"+(no+1)+"前列錶的訊息為");

		Emp cursor = head;
		while (true) {

			System.out.printf(" id=>%d name=%s", cursor.id, cursor.name);
			if (cursor.next == null) {
				break;
			}
			cursor = cursor.next;
		}
		System.out.println();
	}
	
	/** 根據id查詢
	 * @param id
	 * @return
	 */
	public Emp FindEmpById(int id) {
		
		if (head == null) {
			System.out.println(+id+"=>當前鍊表無空");
			return null;
		}
		
		Emp cursor = head;
		while(true) {
			
			if (cursor.id == id) {
				break;
				
			}
			if(cursor.next==null) {
				cursor=null;
				break;
			}
			cursor = cursor.next;
		}
		
		return cursor;
	}

}