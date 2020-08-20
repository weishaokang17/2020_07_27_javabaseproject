package per.wsk.prac.exer03;

/**
 * @Author weishaokang
 * @date 2020-08-04 17:16
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 创建一个Color枚举类，
 * 1)有 RED,BLUE,BLACK,YELLOW,GREEN这个五个枚举值；
 * 2)Color有三个属性redValue，greenValue，blueValue，
 * 3)创建构造方法，参数包括这三个属性，
 * 4)每个枚举值都要给这三个属性赋值，三个属性对应的值分别是red：255,0,0  	blue:0,0,255  black:0,0,0  yellow:255,255,0  green:0,255,0
 * 5)重写toString方法显示三属性的值
 * 6)在Color中添加抽象方法meaning，不同的枚举类的meaning代表的意思各不相同
 *
 */
public enum Color implements Works{
    RED(255,0,0){
        public void meaning(){
            System.out.println("这是红色");
        }
    },
    BLUE(0,0,255){
        public void meaning(){
            System.out.println("这是蓝色");
        }
    },
    BLACK(0,0,0){
        public void meaning(){
            System.out.println("这是黑色");
        }
    },
    YELLOW(255,255,0){
        public void meaning(){
            System.out.println("这是黄色");
        }
    },
    GREEN(0,255,0){
        public void meaning(){
            System.out.println("这是绿色");
        }
    };


    private final int redValue;
    private final int greenValue;
    private final int blueValue;

    private Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public String toString() {
        return "Color{" +
                "redValue=" + redValue +
                ", greenValue=" + greenValue +
                ", blueValue=" + blueValue +
                '}';
    }
}
