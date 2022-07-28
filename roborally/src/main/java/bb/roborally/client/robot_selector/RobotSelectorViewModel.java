package bb.roborally.client.robot_selector;

import bb.roborally.client.RoboRallyModel;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class RobotSelectorViewModel {

    private final RoboRallyModel roboRallyModel;
    private RobotSelectorView view;

    public RobotSelectorViewModel(RoboRallyModel roboRallyModel) {
        this.roboRallyModel = roboRallyModel;
    }

    public void connect(RobotSelectorView view) {
        this.view = view;
        observeModelAndUpdate();
        setupListeners();
    }

    private void observeModelAndUpdate() {
        RobotViewModel robotViewModel1 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(0));
        robotViewModel1.connect(view.getRobotView1());

        RobotViewModel robotViewModel2 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(1));
        robotViewModel2.connect(view.getRobotView2());

        RobotViewModel robotViewModel3 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(2));
        robotViewModel3.connect(view.getRobotView3());

        RobotViewModel robotViewModel4 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(3));
        robotViewModel4.connect(view.getRobotView4());

        RobotViewModel robotViewModel5 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(4));
        robotViewModel5.connect(view.getRobotView5());

        RobotViewModel robotViewModel6 = new RobotViewModel(roboRallyModel.getRobotRegistry().getRobotByFigureId(5));
        robotViewModel6.connect(view.getRobotView6());

    }

    private void setupListeners() {
        view.getRobotView1().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(roboRallyModel.getRobotRegistry().getRobotByFigureId(0).isAvailable()){
                    view.setSelectedRobot(roboRallyModel.getRobotRegistry().getRobotByFigureId(0));
                    selectRobot(0);
                }
            }
        });

        view.getRobotView2().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(roboRallyModel.getRobotRegistry().getRobotByFigureId(1).isAvailable()){
                    view.setSelectedRobot(roboRallyModel.getRobotRegistry().getRobotByFigureId(1));
                    selectRobot(1);
                }
            }
        });

        view.getRobotView3().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(roboRallyModel.getRobotRegistry().getRobotByFigureId(2).isAvailable()){
                    view.setSelectedRobot(roboRallyModel.getRobotRegistry().getRobotByFigureId(2));
                    selectRobot(2);
                }
            }
        });

        view.getRobotView4().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(roboRallyModel.getRobotRegistry().getRobotByFigureId(3).isAvailable()){
                    view.setSelectedRobot(roboRallyModel.getRobotRegistry().getRobotByFigureId(3));
                    selectRobot(3);
                }
            }
        });

        view.getRobotView5().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(roboRallyModel.getRobotRegistry().getRobotByFigureId(4).isAvailable()){
                    view.setSelectedRobot(roboRallyModel.getRobotRegistry().getRobotByFigureId(4));
                    selectRobot(4);
                }
            }
        });

        view.getRobotView6().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(roboRallyModel.getRobotRegistry().getRobotByFigureId(5).isAvailable()){
                    view.setSelectedRobot(roboRallyModel.getRobotRegistry().getRobotByFigureId(5));
                    selectRobot(5);
                }
            }
        });
    }

    private void selectRobot(int figureId) {
        for (int i = 0; i <= 5; i++) {
            if (i == figureId) {
                roboRallyModel.getRobotRegistry().getRobotByFigureId(i).selectProperty().set(true);
            } else {
                roboRallyModel.getRobotRegistry().getRobotByFigureId(i).selectProperty().set(false);
            }
        }
    }

}
