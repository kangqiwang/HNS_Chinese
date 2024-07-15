import requests
import os
import json
import re
import time

class ConditionsAPI:
    def __init__(self,base_url,api_key,out_dir,delay=10) -> None:
        self.base_url=base_url
        self.api_key=api_key
        self.out_dir=out_dir
        self.delay=delay
        os.makedirs(self.out_dir,exist_ok=True)
        
    def save_to_json(self, data, page):
        filename = os.path.join(self.out_dir, f"page_{page}.json")
        with open(filename, 'w') as f:
            json.dump(data, f, indent=4)

    def fetch_data(self, url, page=1):
        headers = {
            'accept': 'application/json',
            'subscription-key': self.api_key,
            "User-agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36"
        }
        
        response = requests.get(url, headers=headers)
        if response.status_code == 200:
            data = response.json()
            self.save_to_json(data, page)
            return data
        else:
            print(f"Failed to fetch data: {response.status_code}")
            
            return None

    def fetch_all_pages(self):
        current_page = 0
        next_page_url = f"{self.base_url}?page={current_page}"
        pageNum=1
        while pageNum >= current_page:
            print(f"Fetching data from page {current_page}...")
            data = self.fetch_data(next_page_url, current_page)
            if not data:
                print("Getting all nhs conditions",data)
                break
            for link in data.get('relatedLink', []):
                if link.get('linkRelationship') == 'Pagination' and link.get('name') == 'Last Page':
                    pageNum=int(re.split(r"page=(\d+)",link.get('url'))[1])
            current_page += 1
            next_page_url = f"{self.base_url}?page={current_page}"
            print(next_page_url)
            time.sleep(self.delay)

