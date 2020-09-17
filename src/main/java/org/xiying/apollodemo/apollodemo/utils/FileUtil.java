package org.xiying.apollodemo.apollodemo.utils;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * 文件基本操作工具
 * </p>
 * @author wangfang5 2017年5月25日 下午1:58:33
 * @version V1.0
 */
public class FileUtil extends FileUtils {

	public static final Logger log = LoggerFactory.getLogger(FileUtil.class);

	private static final String FILE_EXTEND_SPLIT = ".";
	/*** 文件分割符 ****/
	public static final String FILE_SEPARATOR = "/";

	/**
	 * 遍历某个目录获取指定格式的文件绝对路径集合
	 * @param fileDir
	 * @param formats
	 * @return
	 */
	public static List<String> list(File fileDir,String ...formats){
		List<String> resultLists = Lists.newArrayList();
	   if(fileDir.isDirectory()) {
		  File[] files = fileDir.listFiles(file->{
			   if(file.isDirectory()) {
				   return true;
			   }
			   if(formats.length ==0) {
				   return true;
			   }
			   if(ArrayUtils.contains(formats, getExtend(file.getName()))) {
				   return true;
			   }
			   return false;
		   });
		   
		  if(files.length != 0) {
			  Stream.of(files).forEach(file->{
				  if(file.isFile()) {
					  resultLists.add(file.getAbsolutePath());
				  }else {
					  resultLists.addAll(list(file,formats));
				  }
			  });
		  } 
	   }
	   return resultLists;
	}

	/**
	 * 校验目录是否存在
	 * @author panfangbo 2018年5月10日 下午7:29:00
	 * @param directory
	 * @return
	 */
	public static boolean checkDirectory(File directory) {
		if (!directory.exists()) {
			log.error(directory + " does not exist");
			return false;
		}
		if (!directory.isDirectory()) {
			log.error(directory + " is not a directory");
			return false;
		}
		return true;
	}

	/**
	 * 获取文件名（没有扩展内容）
	 * @author wangfang5 2017年5月25日 下午1:59:54
	 * @param fileName 文件名称
	 * @return
	 */
	public static String getNameNoExtend(String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			String name = fileName;
			int fileExtendSplitIndex = name.lastIndexOf(FILE_EXTEND_SPLIT);
			if (-1 != fileExtendSplitIndex && fileExtendSplitIndex != name.length() - 1) {
				name = name.substring(0, fileExtendSplitIndex);
			}
			return name;
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 获取扩展文件类型
	 * @param fileName
	 * @return
	 */
	public static String getExtend(String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			String name = fileName;
			int fileExtendSplitIndex = name.lastIndexOf(FILE_EXTEND_SPLIT);
			if (-1 != fileExtendSplitIndex && fileExtendSplitIndex != name.length() - 1) {
				name = name.substring(fileExtendSplitIndex+1);
				return name;
			}
			return StringUtils.EMPTY;
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 判断文件是否是目录
	 * @author wangfang5 2017年5月25日 下午4:01:45
	 * @param file
	 * @return
	 */
	public static boolean isDirectory(File file) {
		if (isExist(file) && file.isDirectory()) {
			return true;
		}
		return false;
	}

	/**
	 * 重命名
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public static boolean rename(String oldName, String newName) {
		File oldFile = new File(oldName);
		File newFile = new File(newName);

		return oldFile.renameTo(newFile);
	}

	/**
	 * 判断文件是否存在
	 * @author wangfang5 2017年5月25日 下午4:01:16
	 * @param file
	 * @return
	 */
	public static boolean isExist(File file) {
		if (null != file && file.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * 删除目录
	 * @author chenyibin 2014-12-3 下午2:36:27
	 * @param dir
	 * @return
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!FileUtil.isExist(dirFile) || !dirFile.isDirectory()) {
			// System.out.println("删除目录失败"+dir+"目录不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				// 删除子文件
				if (files[i].isFile()) {
					// log.debug("delete file [" + files[i].getName() + "]");
					flag = files[i].delete();
					if (!flag) {
						log.warn("file delete failed. fileName:{}", files[i].getName());
						break;
					}
				}
				// 删除子目录
				else {
					flag = deleteDirectory(files[i].getAbsolutePath());
					break;
				}
			}
		}
		if (!flag) {
			// System.out.println("删除目录失败");
			return false;
		}

		// 删除当前目录
		if (dirFile.delete()) {
			// System.out.println("删除目录"+dir+"成功！");
			return true;
		} else {
			// System.out.println("删除目录"+dir+"失败！");
			return false;
		}
	}

	/**
	 * 删除文件夹以及其下文件，保留特定后缀的文件
	 * @author panfangbo 2018年1月3日 下午11:20:01
	 * @param file
	 * @param fileNameSuffixList
	 * @return
	 */
	public static boolean deleteDirRetainSpecialFile(File file, List<String> fileNameSuffixList) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					deleteDirRetainSpecialFile(files[i], fileNameSuffixList);
				}
			}
			if(file.delete()){
				log.info("delete file[{}]",file.getAbsolutePath());
			}
		} else {
			String fileName = file.getName();
			if (fileName.lastIndexOf(FILE_EXTEND_SPLIT) > 0) {
				String fileNameSuffix = fileName.substring(fileName.lastIndexOf(FILE_EXTEND_SPLIT), fileName.length());
				if (!fileNameSuffixList.contains(fileNameSuffix)) {
					if(file.delete()){
						log.info("delete file[{}]",file.getAbsolutePath());
					}
				}
			} else {
				if(file.delete()){
					log.info("delete file[{}]",file.getAbsolutePath());
				}
			}
		}
		return true;
	}

	public static boolean clearFiles(String dir) {
		File file = new File(dir);
		if (file.exists()) {
			deleteFile(file);
			return true;
		}
		return false;
	}

	public static void deleteFile(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
		}
		if(file.delete()){
			log.info("delete file[{}]",file.getAbsolutePath());
		}
	}

	/**
	 * 清空文件内容
	 * @author panfangbo 2017年12月25日 下午2:38:37
	 * @param filePath
	 */
	public static void clearInfoForFile(String filePath) {
		File file = new File(filePath);
		OutputStreamWriter ow = null;
		try {
			if (!isExist(file)) {
				log.info("file is not exist,filePath:{}", filePath);
				return;
			}
			ow = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
			ow.write("");
			ow.flush();
		} catch (IOException e) {
			log.warn("clear file info failed.filePath:{}", filePath);
		} finally {
			IOUtils.closeQuietly(ow);
		}
	}

	/**
	 * 读取文件内容
	 * @author panfangbo 2018年2月27日 下午1:59:40
	 * @param filePath
	 * @return
	 */
	public static String readFileToString(String filePath) {
		String str = null;
		StringBuilder result = new StringBuilder();
		BufferedReader br = null;
		try {
			File file = new File(filePath);
			if (FileUtil.isExist(file)) {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
				while ((str = br.readLine()) != null) {
					result.append(str).append("\r\n");
				}
			}
		} catch (IOException e) {
			log.warn("read file info failed.filePath:{}", filePath);
		} finally {
			IOUtils.closeQuietly(br);
		}
		return result.toString();
	}
	
	/**
	 * 查找文件夹下符合条件的文件
	 * @author panfangbo 2018年7月16日 下午2:15:07
	 * @param path
	 * @param prefix
	 * @param suffix
	 * @param containStr
	 * @param notContainStr
	 * @return
	 */
	public static String getFilePathWithCondition(String path, String prefix, String suffix, String containStr, String notContainStr) {
		File dir = new File(path);
		String filePath = StringUtils.EMPTY;
		if (FileUtil.isDirectory(dir)) {
			File[] files = dir.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					boolean result = true;
					if (StringUtils.isNotBlank(prefix)) {
						if (!name.startsWith(prefix)) {
							result = false;
						}
					}
					if (StringUtils.isNotBlank(suffix)) {
						if (!name.endsWith(suffix)) {
							result = false;
						}
					}
					if (StringUtils.isNotBlank(notContainStr)) {
						if (name.contains(notContainStr)) {
							result = false;
						}
					}
					if (StringUtils.isNotBlank(containStr)) {
						if (!name.contains(containStr)) {
							result = false;
						}
					}
					return result;
				}
			});
			if (files != null && files.length != 0) {
				filePath = files[0].getAbsolutePath();
			}
		}
		return filePath;
	}
	
	/**
	 * 创建目录(不存在的时候会创建)
	 * @param fileDir
	 * @return
	 */
	public static boolean createDir(String fileDir) {
		File file = new File(fileDir);
		if(!isExist(file)) {
			return file.mkdirs();
		}
		return true;
	}
}
