package guet.edu.cn.zhegui.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
public class ImageUtil {
	
	public static final int TEMPLATE_THREE=3;           //3*3ģ����ֵ�˲�
	public static final int TEMPLATE_FIVE=5;           //5*5ģ����ֵ�˲�
	//��ȡͼ�񣬸���ͼ��ľ��Ե�ַ��ȡͼ��
	/**
	 * ����ͼ���URL����BufferedImageͼ������
	 * @param imageURL
	 * @return
	 */
	public static BufferedImage getImage(String imageURL){
		FileInputStream fis=null;
		BufferedImage bufferedImage=null;;
		try {
			File file = new File("E:/QRcodeTest/��ά��3.jpg");
			fis = new FileInputStream(file);
			bufferedImage = ImageIO.read(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bufferedImage;
	};
	
	//�������ص�תΪRGBֵ���Ҷ�ͼ��
	/**
	 * ӳ��ΪRGB��ģ�ͣ��Ҷ�ͼ��ӳ��
	 * @param alpha
	 * @param red
	 * @param green
	 * @param blue
	 * @return
	 */
	private static int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;
		return newPixel;
	}
	
	
	/**
	 * ͼ��ҶȻ�����
	 * @param image
	 * @return
	 */
	public static BufferedImage grayImage(BufferedImage bufferedImage){
		//BufferedImage bufferedImage = ImageIO.read(new File("E:/QRcodeTest/��ά��3.jpg"));
		BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), bufferedImage.getType());
/*		BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);*/

		for (int i = 0; i < bufferedImage.getWidth(); i++) {
			for (int j = 0; j < bufferedImage.getHeight(); j++) {
				final int color = bufferedImage.getRGB(i, j);
				final int r = (color >> 16) & 0xff;
				final int g = (color >> 8) & 0xff;
				final int b = color & 0xff;
				int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
				if(i==0 && j==10)
					System.out.println(gray);
				//System.out.println(i + " : " + j + " " + gray);
				int newPixel = colorToRGB(255, gray, gray, gray);
				grayImage.setRGB(i, j, newPixel);
			}
		}
/*		File newFile = new File("E:/QRcodeTest/ok22.jpg");
		ImageIO.write(grayImage, "jpg", newFile);*/
		return grayImage;
	}
	
	/**
	 * ����ͼ��
	 * @param bufferedImage
	 * @return
	 */
	public static int saveImage(BufferedImage bufferedImage,String filePath,String formatName){
		
		FileOutputStream newfile=null;
		try {
			File saveFile = new File(filePath);
			if(!saveFile.exists()){
				newfile = new FileOutputStream(saveFile);
			}else{
				//JOptionPane.showMessageDialog(null, "�ļ��Ѵ���", "���ļ��Ѵ���", JOptionPane.ERROR_MESSAGE);
				return -1;
			}
			ImageIO.write(bufferedImage, "jpg", newfile);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
		}
		return 0;
	}
	
	
	/**
	 * ������ֵ��ͼ����ж�ֵ��
	 * @param bufferedImage
	 * @param threshold
	 * @return
	 */
	public static BufferedImage BinarizationIamge(BufferedImage bufferedImage,int threshold){
		BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), bufferedImage.getType());
		for (int i = 0; i < bufferedImage.getWidth(); i++) {
			for (int j = 0; j < bufferedImage.getHeight(); j++) {
				final int color = bufferedImage.getRGB(i, j);
				int gray = (color >> 16) & 0xff;
				if(gray<threshold){
					gray=0;
				}else{
					gray=255;
				}
				int newPixel = colorToRGB(255, gray, gray, gray);
				grayImage.setRGB(i, j, newPixel);
			}
		}
		return grayImage;
	}
	
	/**
	 * Ostu����������ֵ
	 * @param image
	 * @return
	 */
	public static int otsu(BufferedImage image) {  
		
		int width = image.getWidth();
		int height = image.getHeight();
		int pixelSum = width * height, threshold = 0;
		int[] pixelCount = new int[256];
		float[] pixelPro = new float[256];

		int[] data = image.getRGB(0, 0, image.getWidth(), image.getHeight(),
				null, 0, image.getWidth());

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				int c = data[w + h * width];
				data[w + h * width] = (int) (c >> 16) & 0xFF;
			}
		}

		// ��ʼ��
		for (int i = 0; i < 256; i++) {
			pixelCount[i] = 0;
			pixelPro[i] = 0;
		}

		// ͳ�ƻҶȼ���ÿ������������ͼ���еĸ���
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				pixelCount[data[w + h * width]]++;
			}
		}

		// ����ÿ������������ͼ���еı���
		for (int i = 0; i < 256; i++) {
			pixelPro[i] = (float) (pixelCount[i]) / (float) (pixelSum);
		}

		// ����ostu�㷨,�õ�ǰ���ͱ����ķָ�
		// �����Ҷȼ�[0,255],������������ĻҶ�ֵ,Ϊ�����ֵ
		float w0, w1, u0tmp, u1tmp, u0, u1, u, deltaTmp, deltaMax = 0;
		for (int i = 0; i < 256; i++) {
			w0 = w1 = u0tmp = u1tmp = u0 = u1 = u = deltaTmp = 0;

			for (int j = 0; j < 256; j++) {
				if (j <= i) // ��������
				{
					// ��iΪ��ֵ���࣬��һ���ܵĸ���
					w0 += pixelPro[j];
					u0tmp += j * pixelPro[j];
				} else // ǰ������
				{
					// ��iΪ��ֵ���࣬�ڶ����ܵĸ���
					w1 += pixelPro[j];
					u1tmp += j * pixelPro[j];
				}
			}

			u0 = u0tmp / w0; // ��һ���ƽ���Ҷ�
			u1 = u1tmp / w1; // �ڶ����ƽ���Ҷ�
			u = u0tmp + u1tmp; // ����ͼ���ƽ���Ҷ�
			// ������䷽��
			deltaTmp = w0 * (u0 - u) * (u0 - u) + w1 * (u1 - u) * (u1 - u);
			// �ҳ������䷽���Լ���Ӧ����ֵ
			if (deltaTmp > deltaMax) {
				deltaMax = deltaTmp;
				threshold = i;
			}
		}
		// ���������ֵ;
		return threshold; 
	 } 
	
	
	/**
	 * ��ֵ�˲�
	 * @param bufferedImage
	 * @param filterTemplate
	 * @return
	 */
	public static BufferedImage medianFiltering(BufferedImage bufferedImage,int filterTemplate){ 
		//System.out.println("������ֵ�˲���������");
		BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), bufferedImage.getType());
		int height = bufferedImage.getHeight();
		int width = bufferedImage.getWidth();
		int[][] data=new int[width][height];
		int[][] tempData = new int[width][height];
		
		
		for (int i = 0; i < bufferedImage.getWidth(); i++) {
			for (int j = 0; j < bufferedImage.getHeight(); j++) {
				final int color = bufferedImage.getRGB(i, j);
				int gray = (color >> 16) & 0xff;
				data[i][j]=gray;
			}
		}
		
		if(filterTemplate==ImageUtil.TEMPLATE_THREE){
			//3*3����
			System.out.println("wiew222225");
			int[] tempArray= new int[9];
			int count=0;
			for(int x=0;x<width;x++){
				for(int y=0;y<height;y++){
					if(x!=0 && x!=width-1 && y!=0 && y!=height-1){
						tempArray[0] = data[x-1][y-1];  
						tempArray[1] = data[x-1][y];
						tempArray[2] = data[x-1][y+1];
						tempArray[3] = data[x][y-1];  
						tempArray[4] = data[x][y];
						tempArray[5] = data[x][y+1];  
						tempArray[6] = data[x+1][y-1];
						tempArray[7] = data[x+1][y]; 
						tempArray[8] = data[x+1][y+1]; 
						Arrays.sort(tempArray);  
						//minResult = tempArray[4];
						tempData[x][y]=tempArray[4];
					}else{
						tempData[x][y]=data[x][y];
					}
				}
			}
		}else if(filterTemplate==ImageUtil.TEMPLATE_FIVE){
			//5*5����
			System.out.println("wie5555");
			int[] tempArray= new int[25];
			for(int x=0;x<width;x++){
				for(int y=0;y<height;y++){
					if(x>=2 && x<=width-3 && y>=2 && y<=height-3){
						int count=0;
						for(int a=x-2;a<=x+2;a++){
							for(int b=y-2;b<=y+2;b++){
								tempArray[count++]=data[a][b];
							}
						}
						Arrays.sort(tempArray);  
						tempData[x][y]=tempArray[12];
					}else{
						tempData[x][y]=data[x][y];
					}
				}
			}
		}
		
		//��ͼ��ת��
		for(int w=0;w<width;w++){
			for(int h=0;h<height;h++){
				int gray = tempData[w][h];
				//System.out.println(w+","+h+"����ֵ==="+gray);
				int newPixel = colorToRGB(255, gray, gray, gray);
				grayImage.setRGB(w, h, newPixel);
			}
		}
		
		return grayImage;
	}
	
	/**
	 * ��data���鴴��BufferedImage
	 * @param data
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage toImage(int[] data, int width, int height) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		int[] d = new int[width * height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
                d[j+i*width] = (255<<24)
                			    |(data[j+i*width]<<16)
                			    |(data[j+i*width]<<8)
                			    |(data[j+i*width]);
				//d[j+i*width] = data[j+i*width];
			}
		}
		image.setRGB(0, 0, width, height, d, 0, width);
		return image;
	}
	
//	//���ػҶȻ���ͼ����date
//	public static int[][] getGrayIamge(Image image){
//		return null;
//	}
//	
//	//ʹ��OSTU����ͼ���ֵ��
//	public static Image ostuBinarizationIamge(Image imag){
//		return null;
//	} 
//	
//	//ʹ����ֵ�˲���ͼ������˲�����
//	public static Image grayImage(int data){
//		return null;
//	}
}
