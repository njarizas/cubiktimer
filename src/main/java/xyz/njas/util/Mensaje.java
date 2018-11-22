package xyz.njas.util;

import java.io.Serializable;

/**
 *
 * @author Nelson
 */
public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean mensajePendiente;

	private String title;
	private String text;
	private String type;
	private Boolean allowEscapeKey;
	private String customClass;
	private Boolean allowOutsideClick;
	private Boolean showCancelButton;
	private Boolean showConfirmButton;
	private String confirmButtonText;
	private String confirmButtonColor;
	private String cancelButtonText;
	private Boolean closeOnConfirm;
	private Boolean closeOnCancel;
	private String imageUrl;
	private String imageSize;
	private Integer timer;
	private Boolean html;
	private Boolean animation;
	private String inputType;
	private String inputPlaceHolder;
	private String inputValue;
	private Boolean showLoaderOnConfirm;

	public Mensaje() {
		super();
		this.mensajePendiente = false;

		this.title = "Atención";
		this.text = null;
		this.type = null;
		this.allowEscapeKey = true;
		this.customClass = null;
		this.allowOutsideClick = false;
		this.showCancelButton = false;
		this.showConfirmButton = true;
		this.confirmButtonText = "OK";
		this.confirmButtonColor = "#AEDEF4";
		this.cancelButtonText = "Cancel";
		this.closeOnConfirm = true;
		this.closeOnCancel = true;
		this.imageUrl = null;
		this.imageSize = "80x80";
		this.timer = null;
		this.html = false;
		this.animation = true;
		this.inputType = "text";
		this.inputPlaceHolder = null;
		this.inputValue = null;
		this.showLoaderOnConfirm = false;
	}

	public Mensaje(String title) {
		this();
		this.title = title;
	}

	public boolean isMensajePendiente() {
		return mensajePendiente;
	}

	public void setMensajePendiente(boolean mensajePendiente) {
		this.mensajePendiente = mensajePendiente;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getAllowEscapeKey() {
		return allowEscapeKey;
	}

	public void setAllowEscapeKey(Boolean allowEscapeKey) {
		this.allowEscapeKey = allowEscapeKey;
	}

	public String getCustomClass() {
		return customClass;
	}

	public void setCustomClass(String customClass) {
		this.customClass = customClass;
	}

	public Boolean getAllowOutsideClick() {
		return allowOutsideClick;
	}

	public void setAllowOutsideClick(Boolean allowOutsideClick) {
		this.allowOutsideClick = allowOutsideClick;
	}

	public Boolean getShowCancelButton() {
		return showCancelButton;
	}

	public void setShowCancelButton(Boolean showCancelButton) {
		this.showCancelButton = showCancelButton;
	}

	public Boolean getShowConfirmButton() {
		return showConfirmButton;
	}

	public void setShowConfirmButton(Boolean showConfirmButton) {
		this.showConfirmButton = showConfirmButton;
	}

	public String getConfirmButtonText() {
		return confirmButtonText;
	}

	public void setConfirmButtonText(String confirmButtonText) {
		this.confirmButtonText = confirmButtonText;
	}

	public String getConfirmButtonColor() {
		return confirmButtonColor;
	}

	public void setConfirmButtonColor(String confirmButtonColor) {
		this.confirmButtonColor = confirmButtonColor;
	}

	public String getCancelButtonText() {
		return cancelButtonText;
	}

	public void setCancelButtonText(String cancelButtonText) {
		this.cancelButtonText = cancelButtonText;
	}

	public Boolean getCloseOnConfirm() {
		return closeOnConfirm;
	}

	public void setCloseOnConfirm(Boolean closeOnConfirm) {
		this.closeOnConfirm = closeOnConfirm;
	}

	public Boolean getCloseOnCancel() {
		return closeOnCancel;
	}

	public void setCloseOnCancel(Boolean closeOnCancel) {
		this.closeOnCancel = closeOnCancel;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageSize() {
		return imageSize;
	}

	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}

	public Integer getTimer() {
		return timer;
	}

	public void setTimer(Integer timer) {
		this.timer = timer;
	}

	public Boolean getHtml() {
		return html;
	}

	public void setHtml(Boolean html) {
		this.html = html;
	}

	public Boolean getAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getInputPlaceHolder() {
		return inputPlaceHolder;
	}

	public void setInputPlaceHolder(String inputPlaceHolder) {
		this.inputPlaceHolder = inputPlaceHolder;
	}

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public Boolean getShowLoaderOnConfirm() {
		return showLoaderOnConfirm;
	}

	public void setShowLoaderOnConfirm(Boolean showLoaderOnConfirm) {
		this.showLoaderOnConfirm = showLoaderOnConfirm;
	}

}
