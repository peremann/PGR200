package com.lauper.integrationtest;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new App().start();
    }

	private void start() {
		try (Scanner scanner = new Scanner(System.in)){
			System.out.println("Vennligst skriv inn et tall:");
			int pk = scanner.nextInt();
			StuffService stuffService = new StuffService();
			Stuff stuff = stuffService.getStuff(pk);
			System.out.println(stuff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
