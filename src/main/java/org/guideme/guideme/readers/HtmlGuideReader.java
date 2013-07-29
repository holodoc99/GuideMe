package org.guideme.guideme.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.guideme.guideme.model.*;

public class HtmlGuideReader {
	private static Logger log = LogManager.getLogger();
	
	public Guide loadFromFile(File file) {
		try {
			Document doc = Jsoup.parse(file, "UTF-8", "");
			return CreateFromDocument(doc);
		} catch (IOException err) {
			log.error(err);
			return null;
		}
	}
	
	public Guide loadFromString(String html) { 
		Document doc = Jsoup.parse(html, "");
		return CreateFromDocument(doc);
	}
	
	private Guide CreateFromDocument(Document doc) {
		Guide guide = new Guide();
		
		guide.setTitle(doc.select("head title").text());
		guide.setAuthorName(doc.select("head meta[name=author]").attr("content"));
		guide.setKeywordsString(doc.select("head meta[name=keywords]").attr("content"));
		guide.setDescription(doc.select("head meta[name=description]").attr("content"));
		
		return guide;
	}	
}
