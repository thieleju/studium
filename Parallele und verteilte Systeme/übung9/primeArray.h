#include <stdio.h>

#ifndef PRIMEARRAY_H_
#define PRIMEARRAY_H_

#define SIZE 128

static unsigned long long primeArray[SIZE][3];

void setupArray()
{
	primeArray[0][0] = 999999106000090909;
	primeArray[1][0] = 999998948000196587;
	primeArray[2][0] = 999998884000226683;
	primeArray[3][0] = 999998726000343269;
	primeArray[4][0] = 999998654000385329;
	primeArray[5][0] = 999998532000482587;
	primeArray[6][0] = 999998414000577773;
	primeArray[7][0] = 999998294000691509;
	primeArray[8][0] = 999998140000841491;
	primeArray[9][0] = 999998054000919833;
	primeArray[10][0] = 999997940001028139;
	primeArray[11][0] = 999997802001162857;
	primeArray[12][0] = 999997730001247421;
	primeArray[13][0] = 999997668001321531;
	primeArray[14][0] = 999997598001409277;
	primeArray[15][0] = 999997494001525909;
	primeArray[16][0] = 999997424001616919;
	primeArray[17][0] = 999997286001793049;
	primeArray[18][0] = 999997098002047801;
	primeArray[19][0] = 999996932002303427;
	primeArray[20][0] = 999996836002442699;
	primeArray[21][0] = 999996780002530099;
	primeArray[22][0] = 999996724002618019;
	primeArray[23][0] = 999996560002892351;
	primeArray[24][0] = 999996474003035269;
	primeArray[25][0] = 999996234003452053;
	primeArray[26][0] = 999996080003769239;
	primeArray[27][0] = 999995940004046371;
	primeArray[28][0] = 999995816004306239;
	primeArray[29][0] = 999995730004482049;
	primeArray[30][0] = 999995674004605669;
	primeArray[31][0] = 999995512004964247;
	primeArray[32][0] = 999995324005373219;
	primeArray[33][0] = 999995112005908111;
	primeArray[34][0] = 999995020006131979;
	primeArray[35][0] = 999994878006492157;
	primeArray[36][0] = 999994724006880083;
	primeArray[37][0] = 999994610007182369;
	primeArray[38][0] = 999994486007496073;
	primeArray[39][0] = 999994316007967403;
	primeArray[40][0] = 999994084008650539;
	primeArray[41][0] = 999993990008936389;
	primeArray[42][0] = 999993898009218601;
	primeArray[43][0] = 999993768009621247;
	primeArray[44][0] = 999993550010306989;
	primeArray[45][0] = 999993380010846539;
	primeArray[46][0] = 999993236011347323;
	primeArray[47][0] = 999993128011736927;
	primeArray[48][0] = 999992974012283569;
	primeArray[49][0] = 999992888012585111;
	primeArray[50][0] = 999992780012965019;
	primeArray[51][0] = 999992660013402851;
	primeArray[52][0] = 999992506013993353;
	primeArray[53][0] = 999992366014538513;
	primeArray[54][0] = 999992238015022957;
	primeArray[55][0] = 999992120015465519;
	primeArray[56][0] = 999992050015751341;
	primeArray[57][0] = 999991912016294887;
	primeArray[58][0] = 999991808016725687;
	primeArray[59][0] = 999991702017164917;
	primeArray[60][0] = 999991648017393607;
	primeArray[61][0] = 999991568017724927;
	primeArray[62][0] = 999991340018685899;
	primeArray[63][0] = 999991196019328763;
	primeArray[64][0] = 999991076019839219;
	primeArray[65][0] = 999990952020410407;
	primeArray[66][0] = 999990784021152439;
	primeArray[67][0] = 999990608021946791;
	primeArray[68][0] = 999990524022326843;
	primeArray[69][0] = 999990428022785387;
	primeArray[70][0] = 999990256023651703;
	primeArray[71][0] = 999990158024127437;
	primeArray[72][0] = 999990026024802569;
	primeArray[73][0] = 999989942025219017;
	primeArray[74][0] = 999989758026172657;
	primeArray[75][0] = 999989588027068211;
	primeArray[76][0] = 999989450027790281;
	primeArray[77][0] = 999989354028298229;
	primeArray[78][0] = 999989264028773399;
	primeArray[79][0] = 999989162029325561;
	primeArray[80][0] = 999989096029682279;
	primeArray[81][0] = 999989016030124039;
	primeArray[82][0] = 999988928030612327;
	primeArray[83][0] = 999988850031046769;
	primeArray[84][0] = 999988696031909383;
	primeArray[85][0] = 999988610032399901;
	primeArray[86][0] = 999988508032986587;
	primeArray[87][0] = 999988352033876951;
	primeArray[88][0] = 999988244034502043;
	primeArray[89][0] = 999988164034969363;
	primeArray[90][0] = 999988098035362417;
	primeArray[91][0] = 999988032035757631;
	primeArray[92][0] = 999987892036605547;
	primeArray[93][0] = 999987830036983961;
	primeArray[94][0] = 999987734037567893;
	primeArray[95][0] = 999987594038446933;
	primeArray[96][0] = 999987472039210471;
	primeArray[97][0] = 999987312040208311;
	primeArray[98][0] = 999987188040985307;
	primeArray[99][0] = 999987020042041139;
	primeArray[100][0] = 999986876042972819;
	primeArray[101][0] = 999986774043629369;
	primeArray[102][0] = 999986684044232243;
	primeArray[103][0] = 999986550045104521;
	primeArray[104][0] = 999986438045858057;
	primeArray[105][0] = 999986220047348899;
	primeArray[106][0] = 999986098048215277;
	primeArray[107][0] = 999985876049787163;
	primeArray[108][0] = 999985722050885797;
	primeArray[109][0] = 999985594051810309;
	primeArray[110][0] = 999985454052804113;
	primeArray[111][0] = 999985286054044793;
	primeArray[112][0] = 999985160054974031;
	primeArray[113][0] = 999985022056023617;
	primeArray[114][0] = 999984908056865387;
	primeArray[115][0] = 999984776057875463;
	primeArray[116][0] = 999984680058618479;
	primeArray[117][0] = 999984584059358039;
	primeArray[118][0] = 999984446060441729;
	primeArray[119][0] = 999984278061746921;
	primeArray[120][0] = 999984136062865999;
	primeArray[121][0] = 999983932064456947;
	primeArray[122][0] = 999983768065783607;
	primeArray[123][0] = 999983636066848403;
	primeArray[124][0] = 999983554067512753;
	primeArray[125][0] = 999983430068524261;
	primeArray[126][0] = 999983362069088797;
	primeArray[127][0] = 999983220070296619;

	for (unsigned int i = 0; i < SIZE; i++)
	{
		primeArray[i][1] = 0;
		primeArray[i][2] = 0;
	}

	printf("Prime Array initialized\n");
}

void printFactorlist()
{
	for (unsigned int i = 0; i < SIZE; i++)
	{
		// check if the factorization is correct
		unsigned long long product = primeArray[i][1] * primeArray[i][2];
		if (product == primeArray[i][0])
		{
			printf("Korrekt: %llu \t is %llu \t * %llu\n", primeArray[i][0], primeArray[i][1], primeArray[i][2]);
		}
		else
		{
			printf("Falsch: %llu \t is %llu \t * %llu\n", primeArray[i][0], primeArray[i][1], primeArray[i][2]);
		}
	}
}

/*
 * Gibt die aktuelle Zeit in Sekunden zurück.
 * @return aktuelle Zeit
 */
double get_time()
{
  struct rusage usage;
  getrusage(RUSAGE_SELF, &usage);
  return usage.ru_utime.tv_sec + (usage.ru_utime.tv_usec / 1000000.0);
}

/*
 * Faktorisiert eine Zahl in zwei Faktoren.
 * @param number Zahl, die faktorisiert werden soll
 * @param factor1 Pointer auf die erste gefundene Zahl
 * @param factor2 Pointer auf die zweite gefundene Zahl
 */
void factors(unsigned long long number, unsigned long long *factor1, unsigned long long *factor2)
{
  unsigned long long factor = 899999999;
  while (number > 1)
  {
    if (number % factor == 0)
    {
      *factor1 = factor;
      *factor2 = number / factor;
      break;
    }
    factor++;
  }
}

#endif /* PRIMEARRAY_H_ */
