package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.opencv.core.Mat;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;


import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Core;


@Autonomous
public class rightRed extends LinearOpMode {

    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private Servo claw, claw2,Larm,Rarm,wrist;
    private VoltageSensor voltageSensor;
    private double voltageconstant;
    OpenCvCamera cam = null;
    boolean done = false;

    private int width= 1280,height = 720;

    @Override
    public void runOpMode(){
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        backRight = hardwareMap.get(DcMotor.class, "BR");
        frontLeft = hardwareMap.get(DcMotor.class, "FL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        claw = hardwareMap.get(Servo.class, "claw1");
        claw2 = hardwareMap.get(Servo.class,"claw2");
        Larm = hardwareMap.get(Servo.class,"Larm");
        Rarm = hardwareMap.get(Servo.class,"Rarm");
        wrist = hardwareMap.get(Servo.class,"wrist");
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");

        voltageconstant= 12.6/voltageSensor.getVoltage();



        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Rarm.setDirection(Servo.Direction.REVERSE);


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("a", "id", hardwareMap.appContext.getPackageName());
        cam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1") );
        emptyPipeline pipe = new emptyPipeline();
        cam.setPipeline(pipe);
        cam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                telemetry.addData("correct", 1);
                cam.startStreaming(width, height, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {
                telemetry.addData("incorrect", 0);
            }
        });

        telemetry.addLine("Waiting for start");
        telemetry.update();




        waitForStart();

        while (opModeIsActive())
        {
            claw.setPosition(0.9);
            claw2.setPosition(0);
            telemetry.update();
            int initialPropPos = pipe.getPropPosition();

            if (!done){
                telemetry.update();
                if (initialPropPos==1){
                    frontLeft.setPower(0.45*voltageconstant);
                    backLeft.setPower(0.45*voltageconstant);
                    frontRight.setPower(0.45*voltageconstant);
                    backRight.setPower(0.45*voltageconstant);
                    sleep(1000);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(600);
                    frontLeft.setPower(-0.3*voltageconstant);
                    backLeft.setPower(-0.3*voltageconstant);
                    frontRight.setPower(0.3*voltageconstant);
                    backRight.setPower(0.3*voltageconstant);
                    sleep(1200);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(300);
                    frontLeft.setPower(0.3*voltageconstant);
                    backLeft.setPower(0.3*voltageconstant);
                    frontRight.setPower(0.3*voltageconstant);
                    backRight.setPower(0.3*voltageconstant);

                    sleep(700);
                    claw.setPosition(0.6);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(300);
                    frontLeft.setPower(-0.3*voltageconstant);
                    backLeft.setPower(-0.3*voltageconstant);
                    frontRight.setPower(-0.3*voltageconstant);
                    backRight.setPower(-0.3*voltageconstant);
                    sleep(600);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    done = true;

                    //on the backboard hopefully

                    frontLeft.setPower(-0.3*voltageconstant);
                    backLeft.setPower(-0.3*voltageconstant);
                    frontRight.setPower(0.3*voltageconstant);
                    backRight.setPower(0.3*voltageconstant);
                    claw.setPosition(0.9);
                    sleep(100);

                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(300);
                    frontLeft.setPower(0.36*voltageconstant);
                    backLeft.setPower(-0.36*voltageconstant);
                    frontRight.setPower(-0.36*voltageconstant);
                    backRight.setPower(0.36*voltageconstant);
                    sleep(620);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(300);
                    frontLeft.setPower(-0.45*voltageconstant);
                    backLeft.setPower(-0.45*voltageconstant);
                    frontRight.setPower(-0.45*voltageconstant);
                    backRight.setPower(-0.45*voltageconstant);

                    sleep(1250);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    Larm.setPosition(0.8);
                    Rarm.setPosition(0.8);
                    wrist.setPosition(0.45);
                    sleep(1800);
                    claw2.setPosition(0.4);
                    sleep(200);
                    wrist.setPosition(0.71);
                    claw2.setPosition(0);
                    claw.setPosition(0.9);
                    Larm.setPosition(0.04);
                    Rarm.setPosition(0.04);
                    sleep(800);
                    //strafe into corner
                    frontLeft.setPower(-0.4*voltageconstant);
                    backLeft.setPower(0.4*voltageconstant);
                    frontRight.setPower(0.4*voltageconstant);
                    backRight.setPower(-0.4*voltageconstant);
                    sleep(1800);
                    frontLeft.setPower(-0.3*voltageconstant);
                    backLeft.setPower(-0.3*voltageconstant);
                    frontRight.setPower(-0.3*voltageconstant);
                    backRight.setPower(-0.3*voltageconstant);
                    sleep(800);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);

                } else if (initialPropPos==2){
                    frontLeft.setPower(0.45*voltageconstant);
                    backLeft.setPower(0.45*voltageconstant);
                    frontRight.setPower(0.45*voltageconstant);
                    backRight.setPower(0.45*voltageconstant);
                    sleep(1150);
                    claw.setPosition(0.6);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(600);
                    frontLeft.setPower(-0.3*voltageconstant);
                    backLeft.setPower(-0.3*voltageconstant);
                    frontRight.setPower(-0.3*voltageconstant);
                    backRight.setPower(-0.3*voltageconstant);
                    sleep(500);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    done = true;

                    //on the backboard hopefully
                    frontLeft.setPower(-0.3*voltageconstant);
                    backLeft.setPower(-0.3*voltageconstant);
                    frontRight.setPower(0.3*voltageconstant);
                    backRight.setPower(0.3*voltageconstant);
                    claw.setPosition(0.9);
                    sleep(1250);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(300);
                    frontLeft.setPower(0.4*voltageconstant);
                    backLeft.setPower(-0.4*voltageconstant);
                    frontRight.setPower(-0.4*voltageconstant);
                    backRight.setPower(0.4*voltageconstant);
                    sleep(400);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(300);
                    frontLeft.setPower(-0.45*voltageconstant);
                    backLeft.setPower(-0.45*voltageconstant);
                    frontRight.setPower(-0.45*voltageconstant);
                    backRight.setPower(-0.45*voltageconstant);

                    sleep(1250);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    Larm.setPosition(0.8);
                    Rarm.setPosition(0.8);
                    wrist.setPosition(0.45);
                    sleep(1800);
                    claw2.setPosition(0.4);
                    sleep(200);
                    wrist.setPosition(0.71);
                    claw2.setPosition(0);
                    claw.setPosition(0.9);
                    Larm.setPosition(0.04);
                    Rarm.setPosition(0.04);
                    sleep(800);


                    frontLeft.setPower(-0.4*voltageconstant);
                    backLeft.setPower(0.4*voltageconstant);
                    frontRight.setPower(0.4*voltageconstant);
                    backRight.setPower(-0.4*voltageconstant);
                    sleep(1400);
                    frontLeft.setPower(-0.3*voltageconstant);
                    backLeft.setPower(-0.3*voltageconstant);
                    frontRight.setPower(-0.3*voltageconstant);
                    backRight.setPower(-0.3*voltageconstant);
                    sleep(800);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);



                }
                else if (initialPropPos==3){
                    frontLeft.setPower(0.6*voltageconstant);
                    backLeft.setPower(-0.6*voltageconstant);
                    frontRight.setPower(-0.6*voltageconstant);
                    backRight.setPower(0.6*voltageconstant);
                    sleep(800);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(200);
                    frontLeft.setPower(0.65*voltageconstant);
                    backLeft.setPower(0.65*voltageconstant);
                    frontRight.setPower(0.65*voltageconstant);
                    backRight.setPower(0.65*voltageconstant);
                    sleep(600);
                    claw.setPosition(0.6);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(600);
                    frontLeft.setPower(-0.3*voltageconstant);
                    backLeft.setPower(-0.3*voltageconstant);
                    frontRight.setPower(-0.3*voltageconstant);
                    backRight.setPower(-0.3*voltageconstant);
                    sleep(400);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    done = true;

                    //on the backboard hopefully
                    frontLeft.setPower(-0.3*voltageconstant);
                    backLeft.setPower(-0.3*voltageconstant);
                    frontRight.setPower(0.3*voltageconstant);
                    backRight.setPower(0.3*voltageconstant);
                    claw.setPosition(0.9);
                    sleep(1100);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(300);
                    frontLeft.setPower(0.4*voltageconstant);
                    backLeft.setPower(-0.4*voltageconstant);
                    frontRight.setPower(-0.4*voltageconstant);
                    backRight.setPower(0.4*voltageconstant);
                    sleep(100);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    sleep(200);
                    frontLeft.setPower(-0.45*voltageconstant);
                    backLeft.setPower(-0.45*voltageconstant);
                    frontRight.setPower(-0.45*voltageconstant);
                    backRight.setPower(-0.45*voltageconstant);

                    sleep(950);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    Larm.setPosition(0.8);
                    Rarm.setPosition(0.8);
                    wrist.setPosition(0.45);
                    sleep(1800);
                    claw2.setPosition(0.4);
                    sleep(200);
                    wrist.setPosition(0.71);
                    claw2.setPosition(0);
                    claw.setPosition(0.9);
                    Larm.setPosition(0.04);
                    Rarm.setPosition(0.04);
                    sleep(800);
                    frontLeft.setPower(-0.4*voltageconstant);
                    backLeft.setPower(0.4*voltageconstant);
                    frontRight.setPower(0.4*voltageconstant);
                    backRight.setPower(-0.4*voltageconstant);
                    sleep(800);
                    frontLeft.setPower(-0.3*voltageconstant);
                    backLeft.setPower(-0.3*voltageconstant);
                    frontRight.setPower(-0.3*voltageconstant);
                    backRight.setPower(-0.3*voltageconstant);
                    sleep(800);
                    frontLeft.setPower(0);
                    backLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);


                }
            }





            if(gamepad1.a)
            {
                cam.stopStreaming();

            }

            sleep(100);
        }
    }
    class emptyPipeline extends OpenCvPipeline
    {
        Mat YCbCr = new Mat();
        Mat leftCrop;
        Mat midCrop;
        Mat rightCrop;
        Mat output = new Mat();

        double leftavgred;
        double midavgred;
        double rightavgred;
        int propPosition = 0;
        Scalar rectColor = new Scalar(255.0,0.0,0.0);

        @Override
        public Mat processFrame(Mat input)
        {
            Imgproc.cvtColor(input,YCbCr, Imgproc.COLOR_RGB2YCrCb);

            Rect leftRect = new Rect(1,height/2,426,(height/2)-2);
            Rect midRect = new Rect(428,height/2,426,(height/2)-2);
            Rect rightRect = new Rect(855,height/2,425,(height/2)-2);

            input.copyTo(output);
            Imgproc.rectangle(output,leftRect,rectColor,5);
            Imgproc.rectangle(output,midRect,rectColor,5);
            Imgproc.rectangle(output,rightRect,new Scalar(0,255.0,0.0),5);


            leftCrop = YCbCr.submat(leftRect);
            midCrop = YCbCr.submat(midRect);
            rightCrop = YCbCr.submat(rightRect);

            Core.extractChannel(leftCrop, leftCrop, 2);
            Core.extractChannel(midCrop, midCrop, 2);
            Core.extractChannel(rightCrop, rightCrop, 2);

            Scalar leftavg = Core.mean(leftCrop);
            Scalar midavg = Core.mean(midCrop);
            Scalar rightavg = Core.mean(rightCrop);

            leftavgred = leftavg.val[0];
            midavgred = midavg.val[0];
            rightavgred = rightavg.val[0];

            if (leftavgred<midavgred && leftavgred<rightavgred){
                telemetry.addData("leftavg",leftavgred);
                telemetry.addData("midavg",midavgred);
                telemetry.addData("rightavg",rightavgred);
                telemetry.addLine("Left");
                propPosition = 1;

            }
            else if (midavgred<leftavgred && midavgred<rightavgred){
                telemetry.addData("leftavg",leftavgred);
                telemetry.addData("midavg",midavgred);
                telemetry.addData("rightavg",rightavgred);
                telemetry.addLine("Mid");
                propPosition = 2;
            }
            else if (rightavgred<leftavgred && rightavgred<midavgred){
                telemetry.addData("leftavg",leftavgred);
                telemetry.addData("midavg",midavgred);
                telemetry.addData("rightavg",rightavgred);
                telemetry.addLine("Right");
                propPosition = 3;
            }

            return output;

        }

        public int getPropPosition(){
            return propPosition;
        }
    }
}
