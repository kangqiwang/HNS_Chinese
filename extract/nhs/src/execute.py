from api import ConditionsAPI
import logging
import sys
from os import environ as env

if __name__ =="__main__":
    logging.basicConfig(stream=sys.stdout,level=20)
    base_url = "https://api.nhs.uk/conditions/"
    # config_dict = env.copy()
    api_key="2b4002aa80684907970da3ca540b6712"
    out_dir="conditions"
    logging.info("Getting all nhs conditions")
    nhsAPI=ConditionsAPI(base_url,api_key,out_dir)
    logging.info("generate folder")
    nhsAPI.fetch_all_pages()
    # logging.info(config_dict)
    
    # if not api_key:
    #     raise ValueError("API key not found. Please set the API_KEY environment variable.")
    
    # client = NHSApiClient(base_url, api_key)
    # client.fetch_all_pages()
