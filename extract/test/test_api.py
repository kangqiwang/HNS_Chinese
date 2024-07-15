import os
import pytest
import requests
import json
from unittest.mock import patch, mock_open, MagicMock
from extract.nhs.src.api import ConditionsAPI

@pytest.fixture
def client():
    base_url = "https://api.nhs.uk/conditions/"
    api_key = "",
    out_dir="nhs_conditions"
    return ConditionsAPI(base_url, api_key,out_dir)

def test_save_to_json(client):
    test_data = {"key": "value"}
    page = 1
    with patch("builtins.open", mock_open()) as mocked_file:
        client.save_to_json(test_data, page)
        mocked_file.assert_called_once_with(os.path.join(client.out_dir, f"page_{page}.json"), 'w')
        mocked_file().write.assert_called_once_with(json.dumps(test_data, indent=4))

@patch("requests.get")
def test_fetch_data(mock_get, client):
    mock_response = MagicMock()
    mock_response.status_code = 200
    mock_response.json.return_value = {"key": "value"}
    mock_get.return_value = mock_response

    url = "https://api.nhs.uk/conditions/?page=1"
    data = client.fetch_data(url, page=1)
    
    assert data == {"key": "value"}
    mock_get.assert_called_once_with(url, headers={
        'accept': 'application/json',
        'subscription-key': client.api_key
    })

@patch("requests.get")
def test_fetch_all_pages(mock_get, client):
    mock_response_1 = MagicMock()
    mock_response_1.status_code = 200
    mock_response_1.json.return_value = {
        "relatedLink": [
            {
                "url": "https://api.nhs.uk/conditions/?page=2",
                "name": "Next Page",
                "linkRelationship": "Pagination"
            }
        ]
    }

    mock_response_2 = MagicMock()
    mock_response_2.status_code = 200
    mock_response_2.json.return_value = {
        "relatedLink": []
    }

    mock_get.side_effect = [mock_response_1, mock_response_2]

    client.fetch_all_pages()

    assert mock_get.call_count == 2
    mock_get.assert_any_call("https://api.nhs.uk/conditions/?page=1", headers={
        'accept': 'application/json',
        'subscription-key': client.api_key
    })
    mock_get.assert_any_call("https://api.nhs.uk/conditions/?page=2", headers={
        'accept': 'application/json',
        'subscription-key': client.api_key
    })
