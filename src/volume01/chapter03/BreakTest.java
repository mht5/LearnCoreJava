package volume01.chapter03;

/**
 * ����ͨ���ض���ǩ��������ѭ�����ñ�ǩ�������ϣ�������������ѭ��֮ǰ
 * @author mhts
 * @date 2018��6��15��
 */
public class BreakTest {
	public static void main(String[] args) {
		int n = 0;
		System.out.println("Before loop");
		get_out:
		while (n < 5) {
			for (int i = 0; i < 10; i++) {
				System.out.println(i*i);
				if (i > 5) {
//					break;
					break get_out;
				}
			}
			n++;
		}
		System.out.println("After loop");
	}
}
