class vehicle {
    private String name;
    protected float speed;

    protected vehicle (String name)
    {
        this. name = name;
        this.speed = 0.0f;//klo default = 0.0f
    }

    public float move (float distance){
        if (speed > 0){
            return distance / speed;
        }else {
            return -1;
        }
    }

    public String getName()//dari soal pake -/private, tetapi tidak bisa memanggil jika dipake di class lain
    {
        return name;
    }

    public String describe()
    {
        return "Vehicle name : " + name + ", speed : " + speed + " units";
    }

}

class MotorVehicle extends vehicle{
    protected int number_of_wheels;
    protected float engine_volume;

    protected MotorVehicle(String name)
    {
        super(name);
        this.number_of_wheels = 0;
        this.engine_volume = 0.0f;
    }

    public String nonk_nom(){
        return "MotorVehicle : "+getName();
    }

    public void setEngine_volume(float engine_volume)
    {
        this.engine_volume = engine_volume;
    }

    public float getEngine_volume()
    {
        return engine_volume;
    }
}

class Truck extends MotorVehicle{
    private float horsepower;

    public Truck (String name, float horsepower)
    {
        super(name);
        this.horsepower = horsepower;
    }

    public String describe()
    {
        return "nama truck : " +getName()+ "\n"+
                "volume mesin truck : " +getEngine_volume()+ "\n"+
                "deskripsi truck : " +horsepower+ "\n"+
                "horsepower truck : "+horsepower;
    }

    public String nonk_nom()
    {
        return "Truck : "+getName();
    }
}

class Car extends MotorVehicle{
    private int num_doors;

    public Car(String name, int num_doors)
    {
        super(name);
        this.num_doors = num_doors;
    }

    @Override
    public String describe() {
        return "nama car : " +getName()+ "\n"+
                "volume mesin car : " +getEngine_volume()+ "\n"+
                "deskripsi car : " +num_doors+ "\n"+
                "jumlah pintu : "+num_doors;
    }
    public String nonk_nom()
    {
        return "Car :" + getName();
    }
}

class Airplane extends vehicle{
    private float wingspan;
    private int capacity;
    private boolean landingGear;

    public Airplane(String name, float wingspan, int capacity)
    {
        super(name);
        this.wingspan = wingspan;
        this.capacity = capacity;
        this.landingGear = false;
    }

    void landingGear(boolean set)//awalnya pake private tetapi g bisa klo mau dipanggil di main
    {
        this.landingGear = set;
    }

//    public void setLandingGear(boolean set)
//    {
//        landingGear(set);
//    }

    public float move(float distance)
    {
        if (speed > 0)
        {
            return distance / speed;
        }else {
            return -1;
        }
    }

    public String describe(){
        return "nama airplane: " +getName()+ "\n"+
                "wingspan airplane: " +wingspan+ "\n"+
                "deskripsi airplane: " +capacity+ "\n"+
                "capacity airplane: "+capacity + "\n"+
                "landing gear airplane : " +(landingGear ? "Deployed" : "Retracted");
    }
}

public class Main{
    public static void main(String[] args)
    {
        Truck truck = new Truck("Hauler", 400);
        truck.speed = 70;
        truck.setEngine_volume(5.0f);

        System.out.println(truck.describe());
        float time = truck.move(140);
        System.out.println("Time to travels 140 units : "+time+ " hours");
        System.out.println(truck.nonk_nom());

        System.out.println("\n");

        Car car = new Car("Sedan", 4);
        car.speed = 80;
        car.setEngine_volume(2.5f);

        System.out.println(car.describe());
        float timee = car.move(160);
        System.out.println("time to travels 160 units : " +timee+ " hours");
        System.out.println(car.nonk_nom());

        System.out.println("\n");

        Airplane airplane = new Airplane("Boeing 747", 68.4f, 416);
        airplane.speed = 900;

        System.out.println(airplane.describe());
        airplane.landingGear(true);//deploy
        System.out.println(airplane.describe());
        airplane.landingGear(false);//retracted
//        System.out.println("Landing gear retracted : ");
//        System.out.println(airplane.describe());
        float timeee = airplane.move(1800);
        System.out.println("time to travels 1800 units : " +timeee+ " hours");



    }
}
