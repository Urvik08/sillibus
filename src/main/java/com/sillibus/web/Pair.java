package com.sillibus.web;

/**
 Created by joshua on 1/16/16.
 */
public class Pair <L, R> {
	private final L left;
	private final R right;

	public Pair (L left, R right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int hashCode () { return left.hashCode() ^ right.hashCode(); }

	@Override
	public boolean equals (Object o) {
		if (!(o instanceof Pair)) { return false; }
		Pair pairo = (Pair) o;
		return this.left.equals(pairo.getLeft()) && this.right.equals(pairo.getRight());
	}

	public L getLeft () { return left; }

	public R getRight () { return right; }
}