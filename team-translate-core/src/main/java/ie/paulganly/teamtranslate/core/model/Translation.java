package ie.paulganly.teamtranslate.core.model;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonProperty;

@EntityScan
@Entity
@Table(name = "translations")
public class Translation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "english")
	@JsonProperty("english")
	private String english = null;

	@Column(name = "irish")
	@JsonProperty("irish")
	private String irish = null;

	@Column(name = "context")
	@JsonProperty("context")
	private String context = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Translation english(String english) {
		this.english = english;
		return this;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public Translation irish(String irish) {
		this.irish = irish;
		return this;
	}

	public String getIrish() {
		return irish;
	}

	public void setIrish(String irish) {
		this.irish = irish;
	}

	public Translation context(String context) {
		this.context = context;
		return this;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if(this == o){
			return true;
		}
		if(o == null || getClass() != o.getClass()){
			return false;
		}
		Translation translation = (Translation) o;
		return Objects.equals(this.english, translation.english) && Objects.equals(this.irish, translation.irish)
		    && Objects.equals(this.context, translation.context);
	}

	@Override
	public int hashCode() {
		return Objects.hash(english, irish, context);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Translation {\n");

		sb.append("    english: ").append(toIndentedString(english)).append("\n");
		sb.append("    irish: ").append(toIndentedString(irish)).append("\n");
		sb.append("    context: ").append(toIndentedString(context)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	private String toIndentedString(java.lang.Object o) {
		if(o == null){
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
