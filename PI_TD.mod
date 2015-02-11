/*********************************************
 * OPL 12.6.0.0 Model
 * Author: Tiago
 * Creation Date: 11 de Dez de 2014 at 11:45:26
 *********************************************/

//Conjuntos
{int} V = ...; //clientes {1,...n}
{int} R = ...; //rotas
int K=...; // numero total de veiculos

//Parametros com respetivos indices
int Clientes[i in V] = ...; //i=1,2,3...|V|
int Rotas[r in R]=...;
int Custos[c in R]=...;
int ClientesRotas[V][R]=...;

//Variavel decisão
dvar int+ y[r in R] in 0 .. 1; // 


//funcao objectivo
minimize
  sum (r in R) Custos[r] * y[r];
  
subject to{
			
forall(i in V) sum (r in R) ClientesRotas[i][r]*y[r] >= 1;
			
sum (r in R) y[r] <= K;
			  
}