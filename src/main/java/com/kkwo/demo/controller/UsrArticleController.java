package com.kkwo.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkwo.demo.service.ArticleService;
import com.kkwo.demo.service.BoardService;
import com.kkwo.demo.util.Ut;
import com.kkwo.demo.vo.Article;
import com.kkwo.demo.vo.Board;
import com.kkwo.demo.vo.ResultData;
import com.kkwo.demo.vo.Rq;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private Rq rq;

	@RequestMapping("/usr/article/write")
	public String writeArticle() {

		return "usr/article/write";
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String body, int boardId, String replaceUri) {

		if (Ut.empty(title)) {
			return Ut.jsHistoryBack("F-1", "제목을 입력해주세요");
		}

		if (Ut.empty(body)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력해주세요");
		}

		ResultData writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body, boardId);

		int id = (int) writeArticleRd.getData1();

		if (Ut.empty(replaceUri)) {
			replaceUri = "../article/list";
		}

		return Ut.jsReplace("S-1", Ut.f("%d번 글이 생성되었습니다", id), replaceUri);
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id) {

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		model.addAttribute(article);
		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model, @RequestParam(defaultValue = "1") int boardId,
			@RequestParam(defaultValue = "title,body") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "1") int page) {

		Board board = boardService.getBoardById(boardId);

		if (board == null) {
			return rq.jsHistoryBackOnView("F-1", "없는 게시판입니다");
		}

		int articlesCount = articleService.getArticlesCount(boardId, searchKeywordTypeCode,
				searchKeyword);

		int pageSize = 10;
		int totalPage = (int) Math.ceil((double) articlesCount / pageSize);

		List<Article> articles = articleService.getForPrintArticles(boardId, page, pageSize, searchKeywordTypeCode,
				searchKeyword);

		model.addAttribute("page", page);
		model.addAttribute("board", board);
		model.addAttribute("boardId", boardId);		
		model.addAttribute("articles", articles);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("searchKeywordTypeCode", searchKeywordTypeCode);

		return "usr/article/list";
	}

	/* 게시글 수정 */
	@RequestMapping("/usr/article/modify")
	public String showModify(Model model, int id) {

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return rq.jsHistoryBackOnView("F-3", Ut.f("%d번 글은 존재하지 않습니다", id));
		}

		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);

		if (actorCanModifyRd.isFail()) {
			return rq.jsHistoryBackOnView(actorCanModifyRd.getResultCode(), actorCanModifyRd.getResultMsg());
		}

		model.addAttribute("article", article);

		return "usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		Article article = articleService.getArticle(id);

		if (article == null) {
			return Ut.jsHistoryBack("F-3", Ut.f("%d번 글은 존재하지 않습니다", id));
		}

		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);

		if (actorCanModifyRd.isFail()) {
			return Ut.jsHistoryBack(actorCanModifyRd.getResultCode(), actorCanModifyRd.getResultMsg());
		}

		articleService.modifyArticle(id, title, body);

		return Ut.jsReplace(actorCanModifyRd.getResultCode(), Ut.f("%d번 글을 수정했습니다", id),
				Ut.f("../article/detail?id=%d", id));
	}

	/* 게시글 삭제 */
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = articleService.getArticle(id);

		if (article == null) {
			return Ut.jsHistoryBack("F-4", Ut.f("%d번 글은 존재하지 않습니다", id));
		}

		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack("F-4", Ut.f("%d번 글에 대한 권한이 없습니다", id));
		}

		articleService.deleteArticle(id);

		return Ut.jsReplace("S-1", Ut.f("%d번 글을 삭제했습니다", id), "../article/list");
	}
}
