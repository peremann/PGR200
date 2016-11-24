package com.lauper.integrationtest;

import java.sql.SQLException;

public interface StuffDao {
	public Stuff getStuff(int pk) throws SQLException;
}
