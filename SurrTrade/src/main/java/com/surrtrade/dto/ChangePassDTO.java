package com.surrtrade.dto;

import java.util.Objects;

public class ChangePassDTO {

	private String oldPass; 
	private String newPass;
	
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	@Override
	public int hashCode() {
		return Objects.hash(newPass, oldPass);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangePassDTO other = (ChangePassDTO) obj;
		return Objects.equals(newPass, other.newPass) && Objects.equals(oldPass, other.oldPass);
	}
	@Override
	public String toString() {
		return "ChangePassDTO [oldPass=" + oldPass + ", newPass=" + newPass + "]";
	}
}
