package wazzle.view.controller;

import javafx.stage.Stage;
import wazzle.view.WindowCloser;

import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;

public abstract class View<T> implements WindowCloser {
	protected Stage stage;
	protected DoubleProperty visualUnit;
	protected T controller;
	
	public void initialize() {
		this.buildView();
	}
	
	public abstract void nextScene(final ActionEvent event) throws IOException;
	protected abstract void buildView();
	protected abstract void setGraphics();
	
	public void onClose() {
		this.closeWindow(this.stage);
	}
}
