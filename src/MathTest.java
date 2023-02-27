import static org.junit.jupiter.api.Assertions.*;

class MathTest {

    @org.junit.jupiter.api.Test
    void InvalidCountTest1() {
        int count = -1;

        int isquare = -1;

        assertEquals(isquare, Math.fact(count));
    }
    @org.junit.jupiter.api.Test
    void InvalidCountTest2() {
        int count = -100;

        int isquare = -1;

        assertEquals(isquare, Math.fact(count));
    }
    @org.junit.jupiter.api.Test
    void InvalidCountTest3() {
        int count = 15;

        int isquare = -1;

        assertEquals(isquare, Math.fact(count));
    }
    @org.junit.jupiter.api.Test
    void CountZero() {
        int count = 0;

        int isquare = 0;

        assertEquals(isquare, Math.fact(count));
    }
    @org.junit.jupiter.api.Test
    void CountValidTest() {
        int count = 4;

        int isquare = 24;

        assertEquals(isquare, Math.fact(count));
    }

    @org.junit.jupiter.api.Test
    void CountIsZeroTestSign() {
        int count = 0;

        int isquare = 0;

        assertEquals(isquare, Math.sign(count));
    }
    @org.junit.jupiter.api.Test
    void CountIsPlusTestSign() {
        int count = 4;

        int isquare = 1;

        assertEquals(isquare, Math.sign(count));
    }
    @org.junit.jupiter.api.Test
    void CountIsMinsTestSign() {
        int count = -4;

        int isquare = -1;

        assertEquals(isquare, Math.sign(count));
    }

}