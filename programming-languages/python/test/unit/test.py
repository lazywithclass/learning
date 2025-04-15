import pytest
from unittest.mock import patch

from test.src.adder import add
from test.src.subtracter import sub


@pytest.fixture
def create_add_operand():
    return 1

def test_add(create_add_operand):
   assert add(create_add_operand) < 20

@patch("test.src.adder.create", return_value=10)
@patch("test.src.subtracter.create", return_value=10)
def test_add_with_patched_random(adder_create_mock, subtracter_create_mock):
    assert add(1) == 11
    assert sub(10) == 0