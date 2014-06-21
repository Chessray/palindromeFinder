package de.rkl.test.palindromes;

import java.util.*;

/**
 * Created by kleinr on 18/06/2014.
 */
public class RecursiveManachersAlgorithm {

	private static final int MAX_RECURSION_DEPTH = 2;
	private static final int MAX_NUMBER_OF_PALINDROMES = 3;

	public static List<PalindromeInfo> findLongestPalindromes(final String originalString) {
		return findLongestPalindromesWithOffset(originalString, 0, 0);
	}

	private static List<PalindromeInfo> findLongestPalindromesWithOffset(final String completeString, final int offset, final int recursionDepth) {
		if (completeString == null || completeString.length() == 0)
			return Collections.emptyList();

		char[] stringWithBoundaries = addBoundaries(completeString.toCharArray());
		int[] palindromicSpans = new int[stringWithBoundaries.length];
		int centerOfPalindrome = 0;
		int rightMostPalindromeBoundary = 0; // Here the first element in s2 has been processed.
		int m; // The walking indices to compare if two elements are the same
		int n = 0;
		for (int i = 1; i < stringWithBoundaries.length; i++) {
			if (i > rightMostPalindromeBoundary) {
				palindromicSpans[i] = 0;
				m = i - 1;
				n = i + 1;
			} else {
				int mirroredI = centerOfPalindrome * 2 - i;
				if (palindromicSpans[mirroredI] < (rightMostPalindromeBoundary - i)) {
					palindromicSpans[i] = palindromicSpans[mirroredI];
					m = -1; // This signals bypassing the while loop below.
				} else {
					palindromicSpans[i] = rightMostPalindromeBoundary - i;
					n = rightMostPalindromeBoundary + 1;
					m = i * 2 - n;
				}
			}
			while (m >= 0 && n < stringWithBoundaries.length && stringWithBoundaries[m] == stringWithBoundaries[n]) {
				palindromicSpans[i]++;
				m--;
				n++;
			}
			if ((i + palindromicSpans[i]) > rightMostPalindromeBoundary) {
				centerOfPalindrome = i;
				rightMostPalindromeBoundary = i + palindromicSpans[i];
			}
		}
		int len = 0;
		centerOfPalindrome = 0;
		for (int i = 1; i < stringWithBoundaries.length; i++) {
			if (len < palindromicSpans[i]) {
				len = palindromicSpans[i];
				centerOfPalindrome = i;
			}
		}
		char[] ss = Arrays.copyOfRange(stringWithBoundaries, centerOfPalindrome - len, centerOfPalindrome + len + 1);

		final String palindrome = String.valueOf(removeBoundaries(ss));
		final int palindromeIndex = completeString.indexOf(palindrome);
		final List<PalindromeInfo> allInfos = new ArrayList<>();
		if (recursionDepth < MAX_RECURSION_DEPTH) {
			// left side
			allInfos.addAll(findLongestPalindromesWithOffset(completeString.substring(0, palindromeIndex), offset, recursionDepth + 1));
			// riught side
			allInfos.addAll(findLongestPalindromesWithOffset(completeString.substring(palindromeIndex + palindrome.length(), completeString.length()), offset + palindromeIndex + palindrome.length(), recursionDepth + 1));
		}
		final PalindromeInfo palindromeInfo = new PalindromeInfo(palindrome, palindromeIndex + offset);
		allInfos.add(palindromeInfo);
		Collections.sort(allInfos, Comparator.reverseOrder());
		return allInfos.subList(0, Math.min(allInfos.size(), MAX_NUMBER_OF_PALINDROMES));
	}

	private static char[] addBoundaries(char[] characters) {
		if (characters == null || characters.length == 0)
			return "||".toCharArray();

		char[] expandedCharacters = new char[characters.length * 2 + 1];
		for (int i = 0; i < (expandedCharacters.length - 1); i = i + 2) {
			expandedCharacters[i] = '|';
			expandedCharacters[i + 1] = characters[i / 2];
		}
		expandedCharacters[expandedCharacters.length - 1] = '|';
		return expandedCharacters;
	}

	private static char[] removeBoundaries(char[] characters) {
		if (characters == null || characters.length < 3)
			return "".toCharArray();

		char[] cs2 = new char[(characters.length - 1) / 2];
		for (int i = 0; i < cs2.length; i++) {
			cs2[i] = characters[i * 2 + 1];
		}
		return cs2;
	}
}
