package datastruct.huffmanCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hamcrest.core.Is;

public class HuffmanCode {

	public static void main(String[] args) {
// 邏輯步驟
//		String content = "i like like like java do you like a java";
//		byte[] contentBytes = content.getBytes();
//		System.out.println("長度:" + contentBytes.length);// 40
//
//		List<Node> nodes = getNodes(contentBytes);
//		System.out.println("nodes=" + nodes);
//
//		Node createHuffmanTree = createHuffmanTree(nodes);
//		createHuffmanTree.preOrder();
//
//		// 測試
////		getCodes(createHuffmanTree, "", stringBuilder);
//		Map<Byte, String> huffmanCodes = getCodes(createHuffmanTree);
//		System.out.println("huffman編碼錶:" + huffmanCodes);
//		
//		byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//		System.out.println("huffmanCodeByte="+Arrays.toString(huffmanCodeBytes));

//		
//		// 壓縮ZIP//
//		String content = "i like like like java do you like a java";
//		byte[] contentBytes = content.getBytes();
//		byte[] huffmanCodeBytes = hffmanZip(contentBytes);
//		System.out.println("壓縮ZIP的結果=" + Arrays.toString(huffmanCodeBytes) + " 長度=" + huffmanCodeBytes.length);
//
//		// 解碼ZIP//
//		byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
//		System.out.println("解碼ZIP:=" + new String(sourceBytes));
		
		//測試壓縮文件
//		String srcFile="d:"+File.separator+"src.bmp";
//		String dstFile="d:"+File.separator+"dst.zip";
//		
//		zipFile(srcFile, dstFile);
//		System.out.println("壓縮檔案成功");
		
		//測試解壓文件
		String  zipFile="d:"+File.separator+"dst.zip";
		String  dstFile="d:"+File.separator+"src2.bmp";

		unZipFile(zipFile, dstFile);
		System.out.println("解壓縮檔案成功");
	}

	
	//將一個文件的解壓
	/**
	 * @param zipFile 準備解壓的文件
	 * @param dstFile 準備文件的解壓的路徑
	 */
	public static void unZipFile(String zipFile,String dstFile) {
		
		InputStream fis=null;
		
		ObjectInputStream ois=null;
		
		OutputStream os=null;
		
		
		try {
			
			fis=new FileInputStream(zipFile);
			
			ois=new ObjectInputStream(fis);
			
			//讀取byte陣列 huffmanBytes
			byte[] huffmanBytes= (byte[]) ois.readObject();
			
			//讀取賀夫曼碼錶
			Map<Byte, String> codes= (Map<Byte, String>) ois.readObject();
			
			//解碼
			byte[] bytes=decode(codes, huffmanBytes);
			
			
			os=new FileOutputStream(dstFile);
			
			os.write(bytes);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				os.close();
				ois.close();
				fis.close();
			} catch (IOException e) {
			
				System.out.println(e.getMessage());
			}
		}
	}
	
	// 將一個文件進行壓縮
	/**
	 * @param srcFile 傳入的路徑
	 * @param detFile 放到哪個位置
	 */
	@SuppressWarnings("resource")
	public static void zipFile(String srcFile, String detFile) {

		FileInputStream fis = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {

			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(detFile);

			byte[] cbuf = new byte[fis.available()];

			fis.read(cbuf);

			// 直接對原文件進行壓縮
			byte[] hffmanBytes = hffmanZip(cbuf);

			// 創建一個漢文件輸出流關聯的ObjectOutputStream
			oos = new ObjectOutputStream(fos);

			// 把賀夫曼編碼厚的byte陣列寫入壓縮文件
			oos.writeObject(hffmanBytes);

			// 這是為了以後恢復原文件使用,一定要把賀夫曼編碼寫入壓縮文件
			oos.writeObject(huffmanCodes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				oos.close();
				fos.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// 封裝起來使用一個方法即可
	/**
	 * @param bytes 原始字符串對應的byte[]
	 * @return 經過賀夫曼編碼後的byte[]
	 * 
	 *         例如: "i like like like java do you like a java".getBytes()
	 * 
	 */
	private static byte[] hffmanZip(byte[] contentBytes) {

		// 1.
		List<Node> nodes = getNodes(contentBytes);

		// 2. 根據nodes建立賀夫曼樹
		Node createHuffmanTree = createHuffmanTree(nodes);

		// 3.生成賀夫曼樹對應的賀夫曼編碼(根據賀夫曼樹)
		Map<Byte, String> huffmanCodes = getCodes(createHuffmanTree);

		// 4.根據生成的賀夫曼編碼,壓縮得到壓縮後的賀夫曼編碼byte[]
		byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);

		return huffmanCodeBytes;

	}

	// 完成解壓縮思路
	// 1. 將huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24,
	// -14, -117, -4, -60, -90, 28]
	// 轉成 賀夫曼編碼錶對應的二進制字符串"1010100010111..."
	// 2. 在將 賀夫曼編碼對應的二進制的字符串"1010100010111..."=>對照 賀夫曼編碼map =>"i like like like java
	// do you like a java"

	/**
	 * @param huffmanCodes 賀夫曼編碼錶map
	 * @param huffmanBytes 賀夫曼編碼得到的byte陣列
	 * @return 返回就是原來字符串對應的byte陣列
	 */
	private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

		// 1.先得到 huffmanBytes對應的二進制的字符串=>"1010100010111..."
		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < huffmanBytes.length; i++) {

			// 因為當壓縮的時候每8位一組,但最後一個不足8位也值街放入了,因此最後一個位數,無須在"案位與"
			boolean falg = (i == huffmanBytes.length - 1);
			stringBuffer.append(byteToBitString(!falg, huffmanBytes[i]));
		}

		// System.out.println("賀夫曼解碼後對應的二進制字符串="+stringBuffer.toString());

		// 2.把字符串按照賀夫曼編進行解碼
		// 把賀夫曼碼進行交換,因為反向查詢 a->100 100->a
		Map<String, Byte> map = new HashMap<String, Byte>();

		for (Entry<Byte, String> ele : huffmanCodes.entrySet()) {
			map.put(ele.getValue(), ele.getKey());
		}

		// 收集
		List<Byte> list = new ArrayList<Byte>();

		for (int i = 0; i < stringBuffer.length();) {
			int count = 1;// 指針概念
			boolean flag = true;
			Byte b = null;

			while (flag) {

				String key = stringBuffer.substring(i, i + count);
				b = map.get(key);

				if (b == null) {

					count++;
				} else {

					flag = false;
				}

			}

			list.add(b);
			i += count;
		}

		byte[] reB = new byte[list.size()];

		for (int i = 0; i < reB.length; i++) {
			reB[i] = list.get(i);
		}

		return reB;

	}

	/**
	 * 將一個byte轉成一個二進制的字符串
	 * 
	 * @param flag 標誌高位是否要補高位
	 * @param b    傳入的byte
	 * @return 是該b 對應的二進制的字符串(按照補碼返回)
	 * 
	 * 
	 *
	 */
	private static String byteToBitString(boolean flag, byte b) {

		int temp = b;

		if (flag) {
			temp |= 256; // 案位與256 1 0000 0000 | 0000 0001=>1 0000 0001
		}

		String reStr = Integer.toBinaryString(temp);
		if (flag) {
			// 因為原本是否int 有32位 但回傳需要8位(byte)
			return reStr.substring(reStr.length() - 8);
		} else {
			return reStr;
		}
	}

	// 將字符串對應的byte[],通過生成的huffman編碼表,返回一個賀夫曼編碼壓縮後的byte[]

	/**
	 * @param bytes        原始字符串對應的byte[]
	 * @param huffmanCodes 生成的huffman編碼
	 * @return 返回huffman編碼處理後的byte[] 例如:String content = "i like like like java do
	 *         you like a java"; => byte[] contentBytes = content.getBytes();
	 *         返回的是字符串"1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
	 *         =>對應的byte[] huffmanCodesBytes,即8位對應一個byte,放入huffmanCodesBytes
	 */
	private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

		// 1.利用huffmanCodes 將 bytes轉成對應的字符串
		StringBuilder stringBuilder = new StringBuilder();

		for (byte b : bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}

		// 將1010100..轉成byte[]陣列

		// 統計byte[] huffmanCodes長度
		int len = (stringBuilder.length() + 7) / 8;
//System.out.println("ZIP="+stringBuilder.toString());

//		笨的方法
//		int len;
//		if(stringBuilder.length()%8==0) {
//			len=stringBuilder.length()/8;
//		}else {
//			len=stringBuilder.length()/8+1;
//		}

		// 創立壓縮後的byte陣列 ,每8bit對應一個byte
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;
		for (int i = 0; i < stringBuilder.length(); i += 8) {

			String strByte;

			if (i + 8 > stringBuilder.length()) {
				strByte = stringBuilder.substring(i);
			} else {
				strByte = stringBuilder.substring(i, i + 8);
			}
			huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
			index++;
		}
		return huffmanCodeBytes;
	}

	// 生成賀夫曼樹對應的賀夫曼編碼
	// 思路
	// 1.把賀夫曼編碼放在Map<Byte,String> 如32->01 ,97->100.....
	static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
	// 2.生成賀夫曼編碼,需要去拼街路徑
	static StringBuilder stringBuilder = new StringBuilder();

	// 為了方便重載
	private static Map<Byte, String> getCodes(Node root) {

		if (root == null) {
			return null;
		}
		// 向左遞歸
		getCodes(root.left, "0", stringBuilder);

		// System.out.println("------------");打開方便測試~
		// 向右遞歸
		getCodes(root.right, "1", stringBuilder);
		return huffmanCodes;
	}

	/**
	 * *功能:將傳入node節點的 所有葉子節點賀夫曼編碼得到,並放入到huffmanCodes集合
	 * 
	 * @param node          傳入節點
	 * @param code          路徑的值(左子節點是 0 , 右子節點 1)
	 * @param stringBuilder 是用拼街路徑
	 */
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {

		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		stringBuilder2.append(code);

		if (node != null) {
			if (node.data == null) { // 非葉子節點

				// 向左遞歸
				getCodes(node.left, "0", stringBuilder2);
				// 向右遞歸
				getCodes(node.right, "1", stringBuilder2);
			} else { // 葉子節點

				huffmanCodes.put(node.data, stringBuilder2.toString());
				return;
			}
		}
	}

	/**
	 * @param bytes 接收字符陣列
	 * @return 返回list =>[ Node[data=32, weight=9], Node[data=97, weight=5].....]
	 */
	private static List<Node> getNodes(byte[] bytes) {

		ArrayList<Node> nodes = new ArrayList<Node>();

		// 統計每個byte出現的次數->map[符號,次數]
		HashMap<Byte, Integer> counts = new HashMap<Byte, Integer>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) {
				counts.put(b, 1);
			} else {
				counts.put(b, counts.get(b) + 1);
			}
		}

		for (Entry<Byte, Integer> ele : counts.entrySet()) {
			nodes.add(new Node(ele.getKey(), ele.getValue()));
		}
		return nodes;
	}

	public static Node createHuffmanTree(List<Node> nodes) {

		// 處理完後最終只有一個節點
		while (nodes.size() > 1) {

			// 1.排序
			Collections.sort(nodes);
			// System.out.println(nodes); 打開方便測試!!!!!!!!!!!!!!!!!!!!

			// 2.取左小的兩個節點
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);

			// 3.相加
			Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
			parentNode.left = leftNode;
			parentNode.right = rightNode;

			// 4.刪除
			nodes.remove(leftNode);
			nodes.remove(rightNode);

			// 5.加回去
			nodes.add(parentNode);
		}

		return nodes.get(0);

	}
}

class Node implements Comparable<Node> {

	Byte data;// 儲放數據本身
	int weight; // 表示字符出現的次數
	Node left;
	Node right;

	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}

	public void preOrder() {
		System.out.println(this);

		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

}