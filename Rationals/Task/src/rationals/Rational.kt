package rationals

import java.math.BigInteger

data class Rational(val n: BigInteger, val d: BigInteger) : Comparable<Rational> {

    override fun compareTo(other: Rational): Int = n.times(other.d).compareTo(d.times(other.n))

    operator fun plus(r: Rational): Rational = (n.times(r.d).plus(r.n.times(d))).divBy(r.d.times(d))
    operator fun minus(r: Rational): Rational = (n.times(r.d).minus(r.n.times(d))).divBy(r.d.times(d))
    operator fun times(r: Rational): Rational = n.times(r.n).divBy(r.d.times(d))
    operator fun div(r: Rational): Rational = n.times(r.d).divBy(d.times(r.n))
    operator fun unaryMinus(): Rational = Rational(n.negate(), d)

    override fun toString(): String = if (d.toString() == "1") "$n" else "${n}/${d}"
}

infix fun Number.divBy(deno: Number): Rational {
    var nu = BigInteger(this.toString())
    var de = BigInteger(deno.toString())
    if (de.compareTo(BigInteger.ZERO) == 0) throw IllegalArgumentException()  //return 0 if matches

    val gcd = nu.gcd(de)
    val numerator = nu / gcd
    val denominator = de / gcd

    if (denominator.signum() == -1 && numerator.signum() == -1) {   //return 0 if negative
        nu = numerator.negate()
        de = denominator.negate()
    }else if (denominator.signum() == -1){
        nu = numerator.negate()
        de = denominator.negate()
    }else {
        nu = numerator
        de = denominator
    }
    return Rational(nu, de)
}

fun String.toRational(): Rational {
    val d: BigInteger
    val n: BigInteger
    if (this.split('/').count() == 1) {
        n = BigInteger(this.split('/')[0])
        d = BigInteger.ONE
    }else {
        n = BigInteger(this.split('/')[0])
        d = BigInteger(this.split('/')[1])
    }
    return n divBy d
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    val dgf= 5 divBy 6
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}








