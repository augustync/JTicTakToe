/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kolkokrzyzyk;
/**
 *
 * @author Adas
 * klasa zawiera prawie cala logike potzrebna do dzialania gry
 */
public class LogikaGry {
/** zmienna podajaca rozmiar tablicy*/
public static final int SIZEPLANSZA=3;
private char board[][]=new char [SIZEPLANSZA][SIZEPLANSZA];
private char pleyer= 'o';
private  int wi,wj,licz=0;

/** tablica odzwierciedlajaca plansze to
 * char board[][] - z spcyfikatorem ustawionym na private dla pewnosci ze nic przez przpadek
 * nie ulegnie zmianie inne wazne zmienne ustawione na private to
 * char player='o' czyli gra jako kolko za kazdym razem
 * int wi,wj - potzrebne do wstawiania w tablice 'x'
 * konstruktor klasy to LogikaGry()
 */
    public LogikaGry() { }

    /**
     * sprawdza kto wygral
     * @param gracz
     * @return false/true
     */
    @SuppressWarnings("empty-statement")
 public boolean howWin(char gracz){
     boolean end= false;
     
     //wiersze
     for(int i=0; i<board.length; i++)
        end |= (board[i][0]==gracz) && (board[i][1]==gracz) && (board[i][2]==gracz);
     //kolumny
        for(int j=0; j<board[0].length; j++)
            end |= (board[0][j]==gracz) && (board[1][j]==gracz) && (board[2][j]==gracz);
     //przekatna 1
     end |=(board[2][0]==gracz) && (board[1][1]==gracz) && (board[0][2]==gracz);
     //przekontna 2
     end |=(board[0][0]==gracz) && (board[1][1]==gracz) && (board[2][2]==gracz);
    

     return end;
 }// koniec howWin
/** sprawdza remis dla graczy */
 public boolean draw(){
      for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                 if(board[i][j]==' ')  return false;
     return true;
 }
/**
 * MiniMax wywolywany rekurencyjnie
 * na poczatku sprawdzamy stan kto wygral, czy tez remis
 * nastepnie miniMax szuka najlepszego ruchu dal gracza i
 * gdy wygra przekazuje to za pomoca pola mmx sa to wartosci 1,0,-1
 * takie sobie ja wybralem ale mozna inne ogolnie nie zmienito dzialania algorytmu
 * poprostu algorytm analizuje kazdy ruch przeciwnika i zwraca dana wartosc
 * algoryt wykonuje sie kosmiczna ilosc razy
 * jako glowne zrodlo informacji uzylem
 * http://www.google.pl/search?hl=pl&client=firefox-a&channel=s&rls=org.mozilla%3Apl%3Aofficial&hs=RZ8&q=programowanie_gier_logicznych&btnG=Szukaj&lr=
 * @param gracz
 * @return int
 */
 public int miniMax(char gracz){
     int mmx=0,
         m=0;
     if(howWin(gracz)) return (gracz == 'x') ? 1 : -1;
     if(draw()) return 0;

     gracz=(gracz=='x') ? 'o' : 'x';
     mmx= (gracz=='o') ? 1 :-1;
     for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if(board[i][j]==' '){
                    board[i][j]=gracz;
                    m=miniMax(gracz);
                    board[i][j]=' ';
                    if(((gracz=='o') && (m < mmx)) || ((gracz=='x') && (m > mmx))) mmx=m;
                }
            }
     }
     return mmx;
 }
 /**metoda wykonuje ruch za komputer
  * za razem wlasnie w tej metodzie jest wywolywany algo. minimax
  * i ustawiane pola wi i wj
  */
 public void comp(){
    int m=0,
        mmx=-1;
    for (int i = 0; i < board.length; i++){
        for (int j = 0; j < board[i].length; j++){
            if(board[i][j] == ' '){
                board[i][j] = 'x';
                m = miniMax('x');
                board[i][j] = ' ';
            if(m > mmx){
                mmx = m;
                wi = i;
                wj = j;
            }
            }
        }
    }
    }
/** czysci plansze*/
  public void makeBoardEmpty(){
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j]=' ';
    }// koniec empty
/** pobiera literke gracza*/
  public char getGracz(){
      return pleyer;
  }
/**ustawia literke gracza*/
  public void setGracz(char gracz){
      pleyer=gracz;
  }
/**pobiera plansze*/
  public char[][] getBoard(){
      return board;
  }
/**bieze zmienna wi*/
  public int getWi(){
      return wi;
  }
/**bieze zmienna wj*/
  public int getWj(){
      return wj;
  }
  /**metoda sluzy do ustawienia na planszy literki*/
  public void setBoard(int wi,int wj,char gracz){
      board[wi][wj]=gracz;
  }
}
