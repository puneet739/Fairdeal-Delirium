package com.fairdeal;

public class Constants {

	public static class User{
		public static final int AGENT_DELETED=1;
		public static final int AGENT_NOT_DELETED=0;
		
		public static final int MAILER_DISABLED=1;
		public static final int MAILER_ENABLED=0;
		
		public static final String ROLE_ADMIN="ADMIN";
		public static final String ROLE_USER="USER";
		public static final String ROLE_BROKER="BROKER";
	}
	
	public static class Classified{
		public static final int CLASIFIED_DELETED=1;
		public static final int CLASIFIED_NOT_DELETED=0;
		public static final String CLASIFIED_IMAGE1="image1";
		public static final String CLASIFIED_IMAGE2="image2";
		public static final String CLASIFIED_IMAGE3="image3";
		public static final String CLASIFIED_IMAGE4="image4";
	}
	
	public static class Agent{
		public static final int AGENT_DELETED=1;
		public static final int AGENT_NOT_DELETED=0;
		
		public static final String AGENT_MAIN_IMAGE="agentImage";
		public static final String AGENT_IMAGE1="image1";
		public static final String AGENT_IMAGE2="image2";
		public static final String AGENT_IMAGE3="image3";
		public static final String AGENT_IMAGE4="image4";
	}
	
	public static class Config{
		public static final String AGENTS_IMAGE_STORE_PATH="dlm.agents.image.location";
		public static final String ALL_TAGS="dlm.tags.alltags";
		public static final String CLASSIFIED_EXPIRY_DAYS="dlm.classified.expire";
		public static final String CLASSIFIED_IMAGE_STORE_PATH="dlm.classified.image.location";
		public static final String CLASSIFIED_PAGINATION_SIZE="dlm.classified.pagination.size";
	}
}
