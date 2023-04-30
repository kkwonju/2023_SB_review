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

	
	
	@RequestMapping("/usr/article/write")
	public String writeArticle(HttpServletRequest req) {
		
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String title, String body, String replaceUri) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (Ut.empty(title)) {
			return Ut.jsHistoryBack("F-1", "제목을 입력해주세요");
		}
		
		if (Ut.empty(body)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력해주세요");
		}
		
		ResultData writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body);
		
		int id = (int) writeArticleRd.getData1();
	
		if(Ut.empty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", id);
		}
		
		return Ut.jsReplace("S-1", Ut.f("%d번 글이 생성되었습니다", id), replaceUri);
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		model.addAttribute(article);
		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, Model model, int boardId) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		Board board = boardService.getBoardById(boardId);
		if(board == null) {
			return rq.jsHistoryBackOnView("F-1", "없는 게시판입니다");
		}
		
		int articlesCount = articleService.getArticlesCountByBoardId(boardId);
		List<Article> articles = articleService.getForPrintArticles(boardId);
		
		model.addAttribute("board", board);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}

	/* 게시글 수정 */
	@RequestMapping("/usr/article/modify")
	public String showModify(HttpServletRequest req, Model model, int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
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
	public String doModify(HttpServletRequest req , int id, String title, String body) {

		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getArticle(id);

		if (article == null) {
			return Ut.jsHistoryBack("F-3", Ut.f("%d번 글은 존재하지 않습니다", id));
		}

		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);

		if (actorCanModifyRd.isFail()) {
			return Ut.jsHistoryBack(actorCanModifyRd.getResultCode(), actorCanModifyRd.getResultMsg());
		}

		articleService.modifyArticle(id, title, body);

		return Ut.jsReplace(actorCanModifyRd.getResultCode(), Ut.f("%d번 글을 수정했습니다", id), Ut.f("../article/detail?id=%d", id));
	}

	/* 게시글 삭제 */
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String doDelete(HttpServletRequest req , int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");

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
