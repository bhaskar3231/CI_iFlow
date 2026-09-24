def future_date = new Date() + 1 ;
     message.setProperty("future_date", future_date.format("yyyy-MM-dd'T'00:00:00.000")) ;     
        return message;
		