/*********************************************
 * OPL 12.6.0.0 Model
 * Author: Tiago
 * Creation Date: 11 de Dez de 2014 at 11:45:26
 *********************************************/

//Conjuntos
{int} V = ...; //clientes {1,...n}
{int} R = ...; //rotas
{int} C=...;  //custos
//{int} K =...; //veiculos
int K=...; // numero total de veiculos -> SIZE DA TABELA DE VEICULOS

//Parametros com respetivos indices
int Clientes[i in V] = ...; //i=1,2,3...|V|
int Rotas[y in R]=...;
int Custos[c in R]=...;
//int Veiculos[k in K]=...;
int ClientesRotas[V][R]=...;

//Variavel decisão
dvar boolean y[r in R]; // 


//funcao objectivo
minimize
  sum (r in R) Custos[r] * y[r];
  
subject to{
	forall(r in R)
Restricao1:		sum (i in V) ClientesRotas[i][r]*y[r]>=1;	  
Restricao2:	  sum(r in R) y[r]<= K; // k->size da tabela veiculos
	
}