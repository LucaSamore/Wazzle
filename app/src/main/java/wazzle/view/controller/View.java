package wazzle.view.controller;

import javafx.stage.Stage;
import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;

public abstract class View<T> {
	protected Stage stage;
	protected DoubleProperty visualUnit;
	protected T controller;
	
	public void initialize() {
		this.buildView();
	}
	
	public abstract void nextScene(final ActionEvent event) throws IOException;
	protected abstract void buildView();
	protected abstract void setGraphics();
}
