public class Math {
    /**
     * два if'а возвращающие -1 обрабатывают невалидные занчения
     * @param count - это число факториал, которого нужно посчитать
     * for не посредственно считает факториал
     */
    public static int fact(int count){
        if (count == 0) return 0;
        if (count < 0) return -1;
        if (count > 13) return -1;
        int res = 1;
        for(int i = 1; i <= count; i++) {
            res = res * i;
        }
        return res;
    }

    /**
     * @param x - это число, которое нужнот определить, как: отрицательное либо положительно либо ноль
     * @return возвращает -1 в случае если число отрицательное, возвращает 0 в случае если число 0, возвращает 1 в случае если число положительное
     */
    public static int sign(int x){
        if (x > 0){
            return 1;
        } else if (x == 0) {
            return 0;
        } else{
            return -1;
        }
    }
}
