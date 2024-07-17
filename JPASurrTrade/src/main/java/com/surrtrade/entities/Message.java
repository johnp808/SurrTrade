package com.surrtrade.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Message implements Comparable<Message> {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="message_content")
	private String messageContent;
	
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	private boolean seen;
	
	@ManyToOne
	@JoinColumn(name="sender_id")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name="conversation_id")
	private Conversation conversation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	@Override
	public int compareTo(Message otherMessage) {
		return this.createdAt.compareTo(otherMessage.createdAt);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, messageContent, seen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id
				&& Objects.equals(messageContent, other.messageContent) && seen == other.seen;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", messageContent=" + messageContent + ", createdAt=" + createdAt + ", seen="
				+ seen + "]";
	}
}
