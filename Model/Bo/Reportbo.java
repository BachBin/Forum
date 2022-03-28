package Bo;

import java.util.ArrayList;

import Dao.Reportdao;

public class Reportbo {
	Reportdao reportdao = new Reportdao();
	public Long getSLReport(long postId) throws Exception {
		return reportdao.getSLReport(postId);
	}
	public boolean reportPost(long postId, String content) throws Exception {
		return reportdao.reportPost(postId, content);
	}	
	public ArrayList<String> getReportContent(String slug) throws Exception {
		return reportdao.getReportContent(slug);
	}
	public boolean deleteReport(long postId) throws Exception {
		return reportdao.deleteReport(postId);
	}
}
