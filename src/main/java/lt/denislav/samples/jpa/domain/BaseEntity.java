package lt.denislav.samples.jpa.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * Base entity which is extended by all entities.
 * Contains generic fields: id, version. Additional generic fields could be added.
 * 
 * In samples is used to show how entities annotated with {@link MappedSuperclass} work.
 */
@MappedSuperclass
public class BaseEntity {

	/**
	 * Primary key. For field default value generation {@link GenerationType#AUTO} is used.
	 * 
	 * Lines with other strategies can be uncommented to experiment with them.
	 * Use {@link lt.denislav.samples.jpa.EntityPersistTest} to play with different value generations.
	 */
	@Id
	@GeneratedValue
//	@GeneratedValue(strategy=GenerationType.IDENTITY )	
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="custom_sequence")	
//	@SequenceGenerator(name="custom_sequence", sequenceName="hibernate_sequence", allocationSize=3)
//	@GeneratedValue(strategy=GenerationType.TABLE, generator="table_sequence")
//	@TableGenerator(table="table_sequence", name = "table_sequence", allocationSize=2)
	private Long id;
	
	/**
	 * Version field, which serves as entities optimistic lock value.
	 * 
	 * Look at {@link lt.denislav.samples.jpa.OptimisticLockTest} for optimistic lock usage example. 
	 */
	@Version
	private Integer version;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
