package com.kkwo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwo.demo.repository.ReactionPointRepository;

@Service
public class ReactionPointService {
	@Autowired
	private ReactionPointRepository reactionPointRepository;

	public ReactionPointService(ReactionPointRepository reactionPointRepository) {
		this.reactionPointRepository = reactionPointRepository;
	}
	
	public boolean actorCanMakeReaction(int actorId, String relTypeCode, int relId) {
		if(actorId == 0) {
			return false;
		}
		return reactionPointRepository.actorCanMakeReaction(actorId, relTypeCode, relId) == 0;
	}
	
}
