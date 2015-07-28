import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class FileUtil{
	
	private static Logger logger = Logger.getLogger(FileUtil.class);
	/**
	 * 
	 * @Description: �����ļ�
	 * @author duangduangda
	 * @since 2015��7��23��
	 * @param fileName
	 * @param data
	 * @param template
	 * @return
	 */
	public static File createTextToFile(String fileName){
		File file = new File(fileName);
		try {
			if(file.exists()){
				return null;
			}else{
				file.getParentFile().mkdirs();
				file.createNewFile();
				logger.debug("============================"+file.getName()+"������,����·��Ϊ"+file.getAbsolutePath());
			}
		} catch (Exception e){
			logger.error(e);
		}
		return file;
	}

	/**
	 * @Description: ѹ���ļ�
	 * @author duangduangda
	 * @since 2015��7��23��
	 * @param file
	 * @param out
	 * @throws Exception
	 */
	public static void compressFile(File file, ZipOutputStream out) {
		if (!file.exists()) {
			return;
		}
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			ZipEntry entry = new ZipEntry(file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[8192];
			while ((count = bis.read(data, 0, 8192)) != -1) {
				out.write(data, 0, count);
			}
		} catch (Exception e) {
			logger.error(e);
		}finally{
			try {
				if(null != fis){
					fis.close();
				}
				if(null != bis){
					bis.close();
				}
				System.gc();
			} catch (IOException ex) {
				logger.error(ex);
			}
		}
	}
	
	/**
	 * @Description: ɾ����ʱ�ļ���
	 * @author duangduangda
	 * @since 2015��7��24��
	 * @param file
	 */
	public static void deleteFile(File file) {
		boolean flag = false;
		if (file.exists()) {
			if (file.isFile()) {
				flag = file.delete();
				if(flag){
					logger.debug("==============================================="+file.getName()+"�ѱ�ɾ��");
				}
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length && !flag; i++) {
					deleteFile(files[i]);
				}
				flag = file.delete();
				if(flag){
					logger.debug("==============================================="+file.getName()+"�ѱ�ɾ��");
				}
			}
		} else {
			logger.error("��ɾ�����ļ������ڣ�" + '\n');
		}
	}
}
