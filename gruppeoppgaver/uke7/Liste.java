package uke7;

interface Liste<T> {
	int stoerrelse(); //

	void leggTil(int pos, T x);

	void leggTil(T x); //

	void sett(int pos, T x); //

	T hent(int pos);

	T fjern(int pos);

	T fjern();
}
