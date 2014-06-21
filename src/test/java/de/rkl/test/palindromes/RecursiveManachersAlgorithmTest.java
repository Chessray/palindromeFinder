package de.rkl.test.palindromes;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class RecursiveManachersAlgorithmTest {
	@Test
	public void shouldReturnEmptyListForNullString() {
		// When
		final List<PalindromeInfo> palindromeInfos = RecursiveManachersAlgorithm.findLongestPalindromes(null);

		// Then
		assertThat(palindromeInfos, is(notNullValue()));
		assertThat(palindromeInfos.size(), is(equalTo(0)));
	}

	@Test
	public void shouldReturnEmptyListForEmptyString() {
		// When
		final List<PalindromeInfo> palindromeInfos = RecursiveManachersAlgorithm.findLongestPalindromes("");

		// Then
		assertThat(palindromeInfos, is(notNullValue()));
		assertThat(palindromeInfos.size(), is(equalTo(0)));
	}

	@Test
	public void shouldFindExactlyOnePalindromeInCompletePalindromeString() {
		// When
		final List<PalindromeInfo> palindromeInfos = RecursiveManachersAlgorithm.findLongestPalindromes("abba");

		// Then
		assertThat(palindromeInfos, is(notNullValue()));
		assertThat(palindromeInfos.size(), is(equalTo(1)));
		assertThat(palindromeInfos.get(0), is(equalTo(new PalindromeInfo("abba", 0))));
		assertThat(palindromeInfos.get(0).length(), is(equalTo(4)));
	}

	@Test
	public void shouldFindThreeLongestPalindromesInOrderFromLongestToThirdLongest() {
		// When
		final List<PalindromeInfo> palindromeInfos = RecursiveManachersAlgorithm.findLongestPalindromes("sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop");

		// Then
		assertThat(palindromeInfos, is(notNullValue()));
		assertThat(palindromeInfos.size(), is(equalTo(3)));
		assertThat(palindromeInfos.get(0), is(equalTo(new PalindromeInfo("hijkllkjih", 23))));
		assertThat(palindromeInfos.get(0).length(), is(equalTo(10)));
		assertThat(palindromeInfos.get(1), is(equalTo(new PalindromeInfo("defggfed", 13))));
		assertThat(palindromeInfos.get(1).length(), is(equalTo(8)));
		assertThat(palindromeInfos.get(2), is(equalTo(new PalindromeInfo("abccba", 5))));
		assertThat(palindromeInfos.get(2).length(), is(equalTo(6)));
	}
}