
	package entity;

	import java.io.Serializable;

	public class Department {
		private Integer id;
		private String name;
		private int emp_count;
		public Department() {
		}

		public Department(int id, String name,int emp_count) {
			this.id = id;
			this.name = name;
			this.emp_count=emp_count;

		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
				this.name = name;
			}

		public int getEmp_count() {
			return emp_count;
		}

		public void setEmp_count(int emp_count) {
			this.emp_count = emp_count;
		}
		
		
		

	

	
		

	}

	


