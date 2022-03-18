fun main(args: Array<String>) {

    var plansza = arrayOf(charArrayOf('0','0','0','0','0','0','0','0','0','0'),
        charArrayOf('0','0','0','0','0','0','0','0','0','0'),
        charArrayOf('0','0','0','0','0','0','0','0','0','0'),
        charArrayOf('0','0','0','0','0','0','0','0','0','0'),
        charArrayOf('0','0','0','0','0','0','0','0','0','0'),
        charArrayOf('0','0','0','0','0','0','0','0','0','0'),
        charArrayOf('0','0','0','0','0','0','0','0','0','0'),
        charArrayOf('0','0','0','0','0','0','0','0','0','0'),
        charArrayOf('0','0','0','0','0','0','0','0','0','0'),
        charArrayOf('0','0','0','0','0','0','0','0','0','0'))

    var xX = (0..9).random()
    var xY = (0..9).random()
    plansza[xX][xY] = 'x'
    var robotek =intArrayOf(xX,xY)

    var wykX = (0..9).random()
    var wykY = (0..9).random()
    plansza[wykX][wykY] = '!'
    var finish = intArrayOf(wykX,wykY)


    for (row in plansza) {
        println(row.contentToString())
    }
    println("Witamy w grze, mozliwe ruchy to: ")
    println("U - w gore")
    println("D - w dol")
    println("R - w prawo")
    println("L - w lewo")
    println("maksymalna dlugosc ruchu to 5 znakow")

    var ruch:String

    do {
        println("Podaj jaki ruch chcesz wykonac: ")
        ruch = readLine()!!
        wykonywanie_ruchu(ruch, robotek,finish ,plansza)


    } while (((ruch.length > 5) || (!poprawnosc(ruch)) || (!((robotek[0] == wykX) && (robotek[1] == wykY)))))


    println("Gra ukonczona")
}


fun poprawnosc(ruch: String): Boolean {
    var ilosc_dobrych = 0
    val mozliwe_ruchy = "RULD"
    for(i in 0..ruch.length-1){
        var znak = ruch[i]
        for(j in 0..mozliwe_ruchy.length-1){
            var znak_2 = mozliwe_ruchy[j]
            if(znak_2 == znak){
                ilosc_dobrych += 1
                break
            }
            else{ }
        }
    }
    return ilosc_dobrych == ruch.length

}

fun poprzednia_wartosc_pola(polX: Int,polY:Int , mapa: Array<CharArray>): Char{
    var pole:Char
    pole = mapa[polX][polY]

    return pole
}


fun ktore_odwiedziny(mapa: Array<CharArray>, polX: Int,polY: Int, poprzednia: Char){
    val liczba_odwiedzin = charArrayOf('0','a','b','c','d','e','f')

    for(i in 0..liczba_odwiedzin.size-2){
        if(poprzednia == liczba_odwiedzin[i]){
            mapa[polX][polY] = liczba_odwiedzin[i+1]
            break
        }
    }
}

fun wykonywanie_ruchu(ruch: String, rob: IntArray, finish: IntArray, mapa: Array<CharArray>)  {
    for (element in ruch){
        var znak = element
        var poprzednia: Char
        var polX = rob[0]
        var polY = rob[1]
        if((polX == finish[0]) && (polY == finish[1])){
            break
        }else{
            if((znak == 'D') && (polX < 9)){
                rob[0] = rob[0] + 1
                val polXn = rob[0]

                poprzednia = poprzednia_wartosc_pola(polXn, polY ,mapa)
                ktore_odwiedziny(mapa, polX,polY,poprzednia)
                mapa[polXn][polY] = 'x'
            }else if((znak == 'L')  && (polY > 0)){
                rob[1] = rob[1] - 1
                val polYn = rob[1]

                poprzednia = poprzednia_wartosc_pola(polX, polYn ,mapa)
                ktore_odwiedziny(mapa, polX,polY,poprzednia)
                mapa[polX][polYn] = 'x'
            }else if((znak == 'U')  && (polX > 0)){
                rob[0] = rob[0] - 1
                val polXn = rob[0]

                poprzednia = poprzednia_wartosc_pola(polXn, polY ,mapa)
                ktore_odwiedziny(mapa, polX,polY,poprzednia)
                mapa[polXn][polY] = 'x'
            }else if((znak == 'R') && (polY < 9)){
                rob[1] = rob[1] + 1
                val polYn = rob[1]

                poprzednia = poprzednia_wartosc_pola(polX, polYn ,mapa)
                ktore_odwiedziny(mapa, polX,polY,poprzednia)
                mapa[polX][polYn] = 'x'
            }
        }

    }
    for (row in mapa) {
        println(row.contentToString())
    }
}



