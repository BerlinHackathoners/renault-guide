package com.renault.guide.knowledge.domain;

import lombok.Data;

@Data
public class WikiExtract {
	private String displaytitle;
	private ApiUrls ApiUrls;
	private OriginalImage originalImage;
	private String type;
	private String lang;
	private String extract;
	private Namespace namespace;
	private String timestamp;
	private String revision;
	private String title;
	private Thumbnail thumbnail;
	private String dir;
	private String description;
	private String extract_html;
	private ContentUrls content_urls;
	private Titles titles;
	private String tid;
	private Coordinates coordinates;
	private String pageid;

	@Override
	public String toString() {
		return "ClassPojo [displaytitle = " + displaytitle + ", ApiUrls = " + ApiUrls + ", originalImage = " + originalImage + ", type = " + type + ", lang = " + lang + ", extract = " + extract + ", namespace = " + namespace + ", timestamp = " + timestamp + ", revision = " + revision + ", title = " + title + ", thumbnail = " + thumbnail + ", dir = " + dir + ", description = " + description + ", extract_html = " + extract_html + ", content_urls = " + content_urls + ", titles = " + titles + ", tid = " + tid + ", coordinates = " + coordinates + ", pageid = " + pageid + "]";
	}
}
