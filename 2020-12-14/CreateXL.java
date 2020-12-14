import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;


public class CreateXL {
	/** Excel �ļ�Ҫ��ŵ�λ�ã��ٶ���F����*/
	public static String outputFile = "F:\\test.xls";
	public static void main(String argv[]) {

		//�����ά����
		String xf[][]={
				{"���","ѧ��","�γ̴���","�γ�����","ѧ��","�ɼ�","�ƻ�ѧ��","�ƻ�����","�ƻ��γ�","�ƻ�ѧ��","�γ�����"},
				{"1","2018-2019_2","BG000000210","��ѧ���������","3","74","2018-2019_1","BG000000210","��ѧ���������","3","BGͨʶ����"},
				{"2","2019-2020_1","BG0000006X0","��ѧӢ��3","3.5","69","2019-2020_1","BG0000006X0","��ѧӢ��3","3.5","BGͨʶ����"}
		};


		try {
			// �����µ�Excel ������
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			// ��Ҫ�½�һ��Ϊ"Ч��ָ��"�Ĺ����������Ϊ��
			HSSFSheet sheet = workbook.createSheet("ѧ���ɼ�");
			// ������0��λ�ô����У���˵��У�
			HSSFRow[] row = new HSSFRow[xf.length];
			HSSFCell[][] cell = new HSSFCell[xf.length][xf[0].length];
			for(int i=0;i<cell.length;i++){
				row[i] = sheet.createRow((short)i);
			}
			for(int i=0;i<cell.length;i++){
				for(int j=0;j<cell[i].length;j++){
					cell[i][j]= row[i].createCell((short)j);
					cell[i][j].setCellValue(xf[i][j]);
				}
			}
			// �½�һ����ļ���
			FileOutputStream fOut = new FileOutputStream(outputFile);
			// ����Ӧ��Excel ����������
			workbook.write(fOut);
			fOut.flush();
			// �����������ر��ļ�
			fOut.close();
			System.out.println("�ļ�����...");
		} catch (Exception e) {
			System.out.println("������ xlCreate() : " + e);
		}
	}
}