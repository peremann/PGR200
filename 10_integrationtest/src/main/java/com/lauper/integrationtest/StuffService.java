package com.lauper.integrationtest;

import java.sql.SQLException;

public class StuffService {

	private StuffDao stuffDao;
	
	public StuffService(){
		stuffDao = new StuffDaoJDBCImpl();
	}
	
	public Stuff getStuff(int pk) throws SQLException {
		return stuffDao.getStuff(pk);
	}

}
