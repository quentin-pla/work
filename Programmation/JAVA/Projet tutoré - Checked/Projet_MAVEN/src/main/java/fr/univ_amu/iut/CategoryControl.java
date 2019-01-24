package fr.univ_amu.iut;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * A product category controller
 */
public class CategoryControl extends VBox {

    /**
     * List of all the instanced categories controllers
     */
    private static final List<CategoryControl> allCategoriesControllers = new ArrayList<>();

    {
        CategoryControl.allCategoriesControllers.add(this);
    }

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Constructor for non square logos
     *
     * @param category Concerned category
     */
    CategoryControl(Category category) {
        HBox svgContainer;
        int svgHeight;
        int svgWidth;
        String svgContent = category.getPicture().getSvgContent();
        this.myCategory = category;
        svgWidth = category.getPicture().getWidth();
        svgHeight = category.getPicture().getHeight();
        this.setCursor(Cursor.HAND);
        myCategory.setOpen(false);

        categoryContainer = new HBox();

        categoryContainer.setMaxSize(374, 40);
        categoryContainer.setMinSize(374, 40);
        categoryContainer.setPrefSize(374, 40);
        categoryContainer.setStyle("-fx-border-width: 1 0 1 0;-fx-border-color: #595959;");
        categoryContainer.setSpacing(10);
        categoryContainer.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(this, new Insets(-1, 0, 0, 0));

        SVGPath svgIcon = new SVGPath();
        svgIcon.setContent(svgContent);
        iconRegion = new Region();
        iconRegion.setPrefSize(svgWidth, svgHeight);
        iconRegion.setMinSize(svgWidth, svgHeight);
        iconRegion.setMaxSize(svgWidth, svgHeight);
        iconRegion.setStyle("-fx-background-color: black");
        iconRegion.setShape(svgIcon);
        svgContainer = new HBox(iconRegion);
        svgContainer.setPrefSize(15, 15);
        svgContainer.setMinSize(15, 15);
        svgContainer.setMaxSize(15, 15);
        svgContainer.setAlignment(Pos.CENTER);
        HBox.setMargin(svgContainer, new Insets(0, 0, 0, 10));

        categoryTitle = new Label(myCategory.getCategoryTitle());
        categoryTitle.setMinWidth(308);
        categoryTitle.setMaxWidth(308);
        categoryTitle.setPrefWidth(308);
        categoryTitle.setStyle("-fx-font-size: 170%;-fx-text-fill: black");

        SVGPath svgArrow = new SVGPath();
        svgArrow.setContent("M 350 110 L 450 240 L 350 370 L 340 360 L 430 240 L 340 120 Z");
        arrowRegion = new Region();
        arrowRegion.setPrefSize(10, 15);
        arrowRegion.setMinSize(10, 15);
        arrowRegion.setMaxSize(10, 15);
        arrowRegion.setStyle("-fx-background-color: black");
        arrowRegion.setShape(svgArrow);
        arrowRegion.setOpacity(0);
        arrowRegion.setDisable(true);

        categoryContainer.getChildren().addAll(svgContainer, categoryTitle, arrowRegion);

        this.getChildren().add(categoryContainer);
        categoryContainer.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                displayUnderCategories();
            }
        });
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * The concerned category
     */
    private Category myCategory;

    /**
     * Container of the name of the category
     */
    private Label categoryTitle;

    /**
     * Logo container
     */
    private Region iconRegion;

    /**
     * Arrow container
     */
    private Region arrowRegion;

    /**
     * Category container
     */
    private HBox categoryContainer;

    /**
     * The layout of the category
     */
    private double categoryLayout = 1;

    // **************************************************
    // Getters
    // **************************************************

    /**
     * myCategory getter
     *
     * @return attribute myCategory
     */
    public Category getMyCategory() {
        return myCategory;
    }

    /**
     * iconRegion getter
     *
     * @return attribute iconRegion
     */
    public Region getSVGRegion() {
        return iconRegion;
    }

    /**
     * allCategoriesControllers getter
     *
     * @return attribute allCategoriesControllers
     */
    public static List<CategoryControl> getAllCategoriesControllers() {
        return allCategoriesControllers;
    }

    // **************************************************

    /**
     * Make the category looks like an under category
     *
     * @param size The shift size
     */
    private void setAsUnderCategory(double size) {
        this.categoryLayout += 1;
        // this.setMargin ...
        CategoryControl.setMargin(categoryContainer, new Insets(0, 0, 0, this.categoryContainer.getInsets().getLeft() + size));
        this.categoryTitle.setMinWidth(this.categoryTitle.getPrefWidth() - size);
        this.categoryTitle.setMaxWidth(this.categoryTitle.getPrefWidth() - size);
        this.categoryTitle.setPrefWidth(this.categoryTitle.getPrefWidth() - size);
    }

    /**
     * Display under categories if the current category has children
     * Else, display the category's products
     */
    private void displayUnderCategories() {
        if (myCategory.getIsUpperCategory()) {
            if (!myCategory.getOpen()) {
                myCategory.setOpen(true);
                arrowRegion.setRotate(90);
                for (Category c : myCategory.getMyUnderCategories()) {
                    CategoryControl cController = new CategoryControl(c);
                    Category c2 = c;
                    int i = 0;
                    while (!(c2.getParent() == null)) {
                        c2 = c2.getParent();
                        ++i;
                    }
                    cController.setAsUnderCategory(40 * i);
                    if (cController.getMyCategory().getIsUpperCategory()) cController.displayArrow();
                    this.getChildren().add(cController);
                }
            } else {
                myCategory.setOpen(false);
                arrowRegion.setRotate(0);
                this.getChildren().remove(1, this.getChildren().size());

            }
        } else {
            if (!myCategory.getMyProducts().isEmpty()) {
                Main.startNewPage((Stage) this.getScene().getWindow(), new Scene(new ProductViewControl(myCategory)));
            } else System.out.println("vide");
        }
    }

    /**
     * Add an under category to the current category
     *
     * @param category New under category
     */
    void addUnderCategory(Category category) {
        this.setAsUnderCategory(this.categoryLayout * 40);
        this.getChildren().add(new CategoryControl(category));
        myCategory.setUpperCategory(true);
        myCategory.addUnderCategory(category);
        displayArrow();
    }

    /**
     * Update the arrows look to suit the list updates
     */
    public void displayArrow() {
        if (!this.getChildren().isEmpty()) {
            arrowRegion.setOpacity(1);
            arrowRegion.setDisable(false);
        }
    }
}
