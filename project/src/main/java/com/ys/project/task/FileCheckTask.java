package com.ys.project.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ys.project.dao.productionUpload.ProductionUploadMapper;
import com.ys.project.projectVO.Production_uploadVO;

import lombok.AllArgsConstructor;
import lombok.Setter;

/*�߸��� ������ ��ĵ�Ѵ�.*/

@Component // @Bean�� �������� �����ڰ� ���� ��Ʈ�� �ϴ� Ŭ���� �鿡 ���ؼ� @Component�� ���� ���ش� .
@AllArgsConstructor
public class FileCheckTask {

	private static final Logger log = LoggerFactory.getLogger(FileCheckTask.class);
	@Setter(onMethod_ = { @Autowired })
	private ProductionUploadMapper uploadMapper;

	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, -1);

		String str = sdf.format(cal.getTime());

		return str.replace("-", File.separator);

	}

	@Scheduled(cron = "* * 2 * * *")
	public void checkFiles(ServletRequest request) throws Exception {

		String path = request.getServletContext().getRealPath("/resources");

		log.warn("File Check Task rn .....");
		log.warn("" + new Date());

		// ������ ���̽� ���� ����Ʈ ��
		List<Production_uploadVO> fileList = uploadMapper.getOldFiles();

		// ������ ���̽��� �ִ� ���丮 ����Ʈ üũ�� �غ� �Ѵ�.
		List<Path> fileListPaths = fileList.stream()
				.map(vo -> Paths.get(path, vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName()))
				.collect(Collectors.toList());

		// �̹��� ����� ���ϵ� ������ �ִ�.
		fileList.stream().map(vo -> Paths.get(path, "s_" + vo.getUuid() + "_" + vo.getFileName()))
				.forEach(p -> fileListPaths.add(p));

		log.warn("============= ��񿡼� ������ �� ���Ͽ� ���� ����(�Ϸ��� �� ������ ���¸� ���´�.uplodaPath - 1���� ================"
				+ "���ε� �� ���� ������� "
				+ "���ε� �� ������ ������ ===============");

		fileListPaths.forEach(p -> log.warn("" + p));

		// ���� ���丮
		File targetDir = Paths.get(path, getFolderYesterDay()).toFile();

		// ���� �н��� ���� ���� ���丮 �������� �迭 ������ ��´�
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);

		log.warn("======================================");

		for (File file : removeFiles) {
			log.warn("�̰� �ۼַ�Ʈ �н� " + file.getAbsolutePath());
			file.delete();
		}

	}

}
