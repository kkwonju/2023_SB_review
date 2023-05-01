package com.kkwo.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReactionPointRepository {

	@Select("""
			SELECT COUNT(*)
			FROM reactionPoint
			WHERE relTypeCode = 'article'
			AND memberId = #{actorId}
			AND relId = #{relId}
			""")
	public int actorCanMakeReaction(int actorId, String relTypeCode, int relId);
}