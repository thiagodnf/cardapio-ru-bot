package test.thiagodnf.cardapioru.bot.utils;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.runner.RunWith;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.cardapioru.bot.utils.Campuses;

@RunWith(OleasterRunner.class)
public class CampusesTest {{
	
	describe("Creating an instance of this class", () -> {
		
		it("throws an exception when the constructor is called", () -> {
			expect(() -> {
				Constructor<Campuses> c = Campuses.class.getDeclaredConstructor();
				c.setAccessible(true);
				c.newInstance();
			}).toThrow(InvocationTargetException.class);
		});	
	});
	
	describe("Calling getCampus method", () -> {
		
		describe("given an invalid universit", () -> {
			
			it("returns empty one", () -> {
				expect(Campuses.getCampuses("invalid_university").isEmpty()).toBeTrue();
			});
		});
		describe("given a valid universit", () -> {
			
			it("returns the correct size", () -> {
				expect(Campuses.getCampuses("ufpr").size()).toEqual(9);
			});
		});
	});
	
	describe("Calling isValid method", () -> {
		
		describe("given an invalid university", () -> {
			
			it("returns false", () -> {
				expect(Campuses.isValid("invalid_university", "central")).toBeFalse();
			});
		});
		describe("given an invalid campus", () -> {
			
			it("returns false", () -> {
				expect(Campuses.isValid("ufpr", "invalid_campus")).toBeFalse();
			});
		});
		describe("given a valid campus and university", () -> {
			
			it("returns false", () -> {
				expect(Campuses.isValid("ufpr", "central")).toBeTrue();
			});
		});
	});
	
	describe("Calling parse method", () -> {
		
		describe("given an invalid university", () -> {
			
			it("returns 'Desconhecido'", () -> {
				expect(Campuses.getCampusName("invalid_university", "central")).toMatch("Desconhecido");
			});
		});
		describe("given an invalid campus", () -> {
			
			it("returns 'Desconhecido'", () -> {
				expect(Campuses.getCampusName("ufpr", "invalid_campus")).toMatch("Desconhecido");
			});
		});
		describe("given an valid parameters", () -> {
			
			it("returns 'Desconhecido'", () -> {
				expect(Campuses.getCampusName("ufpr", "central")).toMatch("Central");
			});
		});
	});
}}
