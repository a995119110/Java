package com.dao;

import java.util.List;

import com.entities.Score;

public interface UploadDao {
	public void saveOrUpdate(List<Score> scoreList);
}
