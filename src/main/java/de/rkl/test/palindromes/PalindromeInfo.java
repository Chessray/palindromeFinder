package de.rkl.test.palindromes;

import java.util.Objects;

/**
 * Created by kleinr on 18/06/2014.
 */
public final class PalindromeInfo implements Comparable<PalindromeInfo> {
	static final int NO_PALINDROME = -1;
	public final String palindrome;
	public final int index;

	PalindromeInfo(final String palindrome, final int index) {
		this.palindrome = Objects.requireNonNull(palindrome);
		this.index = index;
	}

	public int length() {
		return palindrome.length();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final PalindromeInfo that = (PalindromeInfo) o;

		if (index != that.index) return false;
		if (!palindrome.equals(that.palindrome)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = palindrome.hashCode();
		result = 31 * result + index;
		return result;
	}

	@Override
	public int compareTo(final PalindromeInfo o) {
		final int lengthCompare = Integer.valueOf(length()).compareTo(Integer.valueOf(o.length()));
		return lengthCompare == 0 ? palindrome.compareTo(o.palindrome) : lengthCompare;
	}

	@Override
	public String toString() {
		return "PalindromeInfo{" +
				"palindrome='" + palindrome + '\'' +
				", index=" + index +
				'}';
	}
}
